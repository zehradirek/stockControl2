package com.tobeto.stockcontrol2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
    private String productName;
    private double unitPrice;
    private int unitInStock;
    private int minStock;
}