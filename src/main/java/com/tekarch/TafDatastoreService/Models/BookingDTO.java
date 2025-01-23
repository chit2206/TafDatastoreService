package com.tekarch.TafDatastoreService.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookingDTO {
    private Long userId;
    private Long flightId;
}
