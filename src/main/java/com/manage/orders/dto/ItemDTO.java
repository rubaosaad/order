package com.manage.orders.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemDTO {
	
	private Long id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String name;

}
