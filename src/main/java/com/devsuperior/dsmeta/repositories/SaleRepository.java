package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	//
	@Query(nativeQuery = true, value = " SELECT TB_SELLER.NAME as sellerName, SUM(TB_SALES.AMOUNT) as total "
			+ " FROM TB_SALES "
			+ " INNER JOIN TB_SELLER ON TB_SELLER.ID = TB_SALES.SELLER_ID "
			+ " WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate "
			+ " GROUP BY TB_SELLER.NAME" )
	Page<Sale> searchSummaryByDate(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
