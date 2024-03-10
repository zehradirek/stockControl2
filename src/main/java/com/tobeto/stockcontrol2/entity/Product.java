package com.tobeto.stockcontrol2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "unit_in_stock")
	private int unitInStock;

	@Column(name = "unit_price")
	private double unitPrice;

	@Column(name = "min_stock")
	private int minStock;
}


