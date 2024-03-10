package com.tobeto.stockcontrol2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private UUID id;
    private String productName;
    private double unitPrice;
    private int unitInStock;
    private int minStock;
}
