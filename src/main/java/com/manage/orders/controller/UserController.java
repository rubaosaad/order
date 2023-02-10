package com.manage.orders.controller;

import com.manage.orders.dto.UserDTO;
import com.manage.orders.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "Operations to manage users")
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping
	@Operation(summary = "Create user")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(userService.addUser(userDTO));
	}
	@PatchMapping
	@Operation(summary = "Update user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(userService.updateUser(userDTO));
	}
	
	@DeleteMapping
	@Operation(summary = "Delete user")
	public ResponseEntity<String> deleteUser(@RequestBody Long id){
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@GetMapping("/{id}")
	@Operation(summary = "Get user passing ID")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		return ResponseEntity.ok(userService.getUser(id));
	}

	@GetMapping
	@Operation(summary = "Get all users")
	public ResponseEntity<List<UserDTO>> getAll() {
		return ResponseEntity.ok(userService.getAll());
	}

}
