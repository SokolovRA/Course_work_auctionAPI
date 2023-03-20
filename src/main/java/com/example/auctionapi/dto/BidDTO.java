package com.example.auctionapi.dto;

import com.example.auctionapi.model.Bid;
import com.example.auctionapi.model.Lot;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BidDTO {
    @JsonIgnore
    private Long id;
    private String bidderName;
    @JsonIgnore
    private LocalDateTime bidDate;
    @JsonIgnore
    private Long lot;

}
