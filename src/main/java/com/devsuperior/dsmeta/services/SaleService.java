package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Transactional(readOnly = true)
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	

	@Transactional(readOnly = true)
	public Page<SaleSummaryDTO> findSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable) {
		LocalDate today = LocalDate.now();
		
		LocalDate initialDate;
	
		if(minDate != null) {
			initialDate = minDate;
		} else if(minDate == null && maxDate != null) {
			initialDate = maxDate.minusYears(1);
		} else {
			initialDate = today.minusYears(1);
		}
				
        LocalDate finalDate = maxDate != null ? maxDate : today;  
        
        return repository.searchSummaryByDate(initialDate, finalDate, pageable);
	}
	
	
}
