package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleReportDTO {
	Long id;
	LocalDate date;
	Double amount;
	String sellerName;

	public SaleReportDTO(Long id, LocalDate date, Double amount, String sellerName) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.sellerName = sellerName;
	}

	public Long getId() {
		return id;
	}

	public Double getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getSellerName() {
		return sellerName;
	}
	
}
