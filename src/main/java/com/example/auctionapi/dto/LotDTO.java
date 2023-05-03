package com.example.auctionapi.dto;

import com.example.auctionapi.enums.LotStatus;

import lombok.Data;
import lombok.NoArgsConstructor;



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