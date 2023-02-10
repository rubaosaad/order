package com.manage.orders.controller;

import com.manage.orders.dto.ItemDTO;
import com.manage.orders.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@Tag(name = "Itens", description = "Itens methods")
public class ItemContoller {
	
	@Autowired
	private ItemService itemService;


	@PostMapping
	@Operation(summary = "Create item")
	public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDTO) throws Exception{
		return ResponseEntity.ok(itemService.addItem(itemDTO));
	}
	@PatchMapping
	@Operation(summary = "Update item")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO) throws Exception{
		return ResponseEntity.ok(itemService.updateItem(itemDTO));
	}
	@DeleteMapping
	@Operation(summary = "Delete item")
	public ResponseEntity<Void> deleteItem(@RequestBody Long id) throws Exception {
		itemService.deleteItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@Operation(summary = "Get item passing ID")
	public ResponseEntity<ItemDTO> getItem(@PathVariable Long id){
		return ResponseEntity.ok(itemService.getItem(id));
	}

	@GetMapping
	@Operation(summary = "Get all itens")
	public ResponseEntity<List<ItemDTO>> getAll() {
		return ResponseEntity.ok(itemService.getAll());
	}


}
