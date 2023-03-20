package com.example.auctionapi.dto;

import com.example.auctionapi.enums.LotStatus;
import com.example.auctionapi.model.Bid;
import com.example.auctionapi.model.Lot;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
public class LotDTO {
    private Long id;
    private LotStatus status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;


}