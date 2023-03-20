package com.example.auctionapi.service;

import com.example.auctionapi.dto.CreateLotDTO;
import com.example.auctionapi.dto.LastBidDTO;
import com.example.auctionapi.dto.LotDTO;
import com.example.auctionapi.enums.LotStatus;
import com.example.auctionapi.model.Lot;
import com.example.auctionapi.projection.BidderNameAndBidDate;
import com.example.auctionapi.repository.BidRepository;
import com.example.auctionapi.repository.LotRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.auctionapi.mapping.MappingUtils;
import com.example.auctionapi.dto.FullLotDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public LotService(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }

    public LotDTO getLotById(Long id) {
        return MappingUtils.fromLotToLotDTO(lotRepository.findById(id).orElse(null));
    }

    public LotDTO createLot(CreateLotDTO createdLotDTO){
        Lot lot = MappingUtils.fromCreatedLotDTOToLot(createdLotDTO);
        lot.setStatus(LotStatus.CREATED);
        Lot createdLot = lotRepository.save(lot);

        return MappingUtils.fromLotToLotDTO(createdLot);
    }
    public void updateLotStatusToStarted(Long lotId) {
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.STARTED);
        lotRepository.save(lot);
    }

    public void updateLotStatusToStopped(Long lotId) {
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.STOPPED);
        lotRepository.save(lot);
    }

    public BidderNameAndBidDate getInfoAboutFirstBidder(Long id){
        return bidRepository.getInfoAboutFirstBidder(id);
    }

    public BidderNameAndBidDate getInfoAboutLudoman(Long id) {
        return bidRepository.getInfoAboutLudoman(id);
    }

    public FullLotDTO getInfoAboutLot(Long id) {
        FullLotDTO fullLotDTO = MappingUtils.fromLotDTOToFullLotDTO(getLotById(id));
        Integer currentPrice = sumCurrentPrice(id, fullLotDTO.getBidPrice(), fullLotDTO.getStartPrice());
        fullLotDTO.setCurrentPrice(currentPrice);
        fullLotDTO.setLastBid(findInfoABoutLastBid(id));
        return fullLotDTO;
    }

    public Collection<LotDTO> getAllLots(LotStatus lotStatus, Integer pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        return lotRepository.findAllByStatus(lotStatus, pageRequest).stream()
                .map(MappingUtils::fromLotToLotDTO)
                .collect(Collectors.toList());
    }

    public Collection<FullLotDTO> getAllLotsForExport() {
        return lotRepository.findAll().stream()
                .map(MappingUtils::fromLotToFullLotDTO)
                .peek(lot -> lot.setCurrentPrice(sumCurrentPrice(lot.getId(), lot.getBidPrice(), lot.getStartPrice())))
                .peek(lot -> lot.setLastBid(findInfoABoutLastBid(lot.getId())))
                .collect(Collectors.toList());
    }

    public boolean checkToException (CreateLotDTO createLotDTO) {
        if(createLotDTO.getTitle() == null || createLotDTO.getTitle().isEmpty()) {
            return false;
        } else if (createLotDTO.getDescription() == null || createLotDTO.getDescription().isEmpty()) {
            return false;
        } else if (createLotDTO.getStartPrice() == null || createLotDTO.getBidPrice() == null) {
            return false;
        } else {
            return true;
        }
    }
    private Integer sumCurrentPrice(Long id, Integer bidPrice, Integer startPrice) {
        return (int) (bidRepository.numberOfBets(id) * bidPrice + startPrice);
    }

    private LastBidDTO findInfoABoutLastBid(Long id) {
        if(bidRepository.numberOfBets(id) != 0) {
            LastBidDTO bidDTO = new LastBidDTO();
            bidDTO.setBidderName(bidRepository.getInfoAboutLastBidDate(id).getBidderName());
            bidDTO.setBidDate(bidRepository.getInfoAboutLastBidDate(id).getBidDate());
            return bidDTO;
        } else {
            return null;
        }
    }
}
