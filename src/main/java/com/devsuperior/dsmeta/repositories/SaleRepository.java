package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {


	//sumario de vendas por vendedor:
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) "
			+ "FROM Sale obj "
			+ "WHERE obj.date BETWEEN :minDate AND :maxDate " 
			+ "GROUP BY obj.seller.name")
	Page<SaleSummaryDTO> searchSummaryByDate(LocalDate minDate, LocalDate maxDate, Pageable pageable);
	
	
	//relatorio de vendas por vendedor:
		@Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
				+ "FROM Sale obj "
				+ "WHERE obj.date BETWEEN :minDate AND :maxDate "
				+ "AND (LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL) " 
				+ "GROUP BY obj.id, obj.seller.name")
		Page<SaleReportDTO> searchReportByDate(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
