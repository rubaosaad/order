package com.manage.orders.controller;

import com.manage.orders.dto.StockMovementDTO;
import com.manage.orders.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@Tag(name = "Stock", description = "Stock Apis")
public class StockController {
	
	@Autowired
	private StockService stockService;

	@PostMapping
	@Operation(summary = "Create Stock")
	public ResponseEntity<StockMovementDTO> addStock(@RequestBody StockMovementDTO stockMovementDTO)  throws Exception{
		return ResponseEntity.ok(stockService.addStock(stockMovementDTO));
	}
	@PatchMapping
	@Operation(summary = "Update stock")
	public ResponseEntity<StockMovementDTO> updateStock(@RequestBody StockMovementDTO stockMovementDTO)  throws Exception{
		return ResponseEntity.ok(stockService.updateStock(stockMovementDTO));
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete stock")
	public ResponseEntity<Void> deleteStock(@PathVariable Long id) throws Exception{
		stockService.deleteStock(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@Operation(summary = "Get stock passing ID")
	public ResponseEntity<StockMovementDTO> getStock(@PathVariable Long id) throws Exception{
		return ResponseEntity.ok(stockService.getStock(id));
	}
	@GetMapping
	@Operation(summary = "Get all stock")
	public ResponseEntity<List<StockMovementDTO>> getAll() {
		return ResponseEntity.ok(stockService.getAll());
	}


}
