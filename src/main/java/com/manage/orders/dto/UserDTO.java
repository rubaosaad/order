package com.manage.orders.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
	
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String name;
	@NotEmpty(message="Preenchimento obrigatório")
	private String email;

}
