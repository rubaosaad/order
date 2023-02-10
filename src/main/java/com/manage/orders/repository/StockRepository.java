package com.manage.orders.repository;

import com.manage.orders.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockMovement, Long>{
	
	Optional<StockMovement> findByItemId(Long id);

}
