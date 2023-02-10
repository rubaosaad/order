package com.manage.orders.controller;

import com.manage.orders.dto.OrderDTO;
import com.manage.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Tag(name = "Order", description = "Orders methods")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@PostMapping
	@Operation(summary = "Create order")
	public ResponseEntity<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) throws Exception {
		return ResponseEntity.ok(orderService.addOrder(orderDTO));
	}
	@PatchMapping
	@Operation(summary = "Update order")
	public ResponseEntity<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) throws Exception {
		return ResponseEntity.ok(orderService.updateOrder(orderDTO));
	}
	@DeleteMapping
	@Operation(summary = "Delete order")
	public ResponseEntity<Void> deleteOrder(@RequestBody Long id) throws Exception {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@Operation(summary = "Get order passing ID")
	public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) throws Exception {
		return ResponseEntity.ok(orderService.getOrder(id));
	}
	@GetMapping
	@Operation(summary = "Get all orders")
	public ResponseEntity<List<OrderDTO>> getAll() {
		return ResponseEntity.ok(orderService.getAll());
	}

}
