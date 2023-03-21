package com.example.auctionapi.dto;

import com.example.auctionapi.enums.LotStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class FullLotDTO {
  private Long id;
  private LotStatus status;
  private String title;
  private String description;
  private Integer startPrice;
  private Integer  bidPrice;
  private Integer currentPrice;
  private LastBidDTO lastBid;


}
