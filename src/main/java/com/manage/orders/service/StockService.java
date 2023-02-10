package com.manage.orders.service;


import com.manage.orders.domain.StockMovement;
import com.manage.orders.dto.StockMovementDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StockService {

	List<StockMovementDTO> getAll();

	StockMovementDTO addStock(StockMovementDTO stockMovementDTO) throws Exception;

	StockMovementDTO getStock(Long id) throws Exception;

	StockMovementDTO updateStock(StockMovementDTO stockMovementDTO) throws Exception;

	void deleteStock(Long id) throws Exception;

	void updateItemsStock(Long id, Integer quantity);

	Optional<StockMovement> findByItemId(Long id);

}
