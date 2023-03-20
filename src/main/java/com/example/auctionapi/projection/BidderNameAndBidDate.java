package com.example.auctionapi.projection;

import java.time.LocalDateTime;

public interface BidderNameAndBidDate {
    String getBidderName();
    LocalDateTime getBidDate();
}
