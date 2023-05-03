package com.example.auctionapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class LastBidDTO {
    private String bidderName;
    private LocalDateTime bidDate;
}
