package com.example.auctionapi.service;

import com.example.auctionapi.dto.BidDTO;
import com.example.auctionapi.dto.LotDTO;
import com.example.auctionapi.model.Bid;
import com.example.auctionapi.model.Lot;
import com.example.auctionapi.repository.BidRepository;
import com.example.auctionapi.repository.LotRepository;
import org.springframework.stereotype.Service;
import com.example.auctionapi.mapping.MappingUtils;

import java.time.LocalDateTime;

@Service
public class BidService {
    private  final BidRepository bidRepository;
    private  final LotService lotService;

    public BidService(BidRepository bidRepository, LotService lotService) {
        this.bidRepository = bidRepository;
        this.lotService = lotService;
    }
    public BidDTO createBid(BidDTO bidDTO) {
        Lot lot = MappingUtils.fromLotDTOToLot(lotService.getLotById(bidDTO.getLot()));
        Bid bid = MappingUtils.fromBidDTOtoBid(bidDTO);
        bid.setLot(lot);
        bid.setBidDate(LocalDateTime.now());
        Bid createdBid = bidRepository.save(bid);
        return MappingUtils.fromBidToBidDTO(createdBid);
    }
}
