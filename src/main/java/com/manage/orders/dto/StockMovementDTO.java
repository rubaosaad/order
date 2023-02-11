package com.manage.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.orders.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockMovementDTO {
	
	private Long id;
	@JsonIgnore
	private Date creationDate;
	private Item item;
	private Integer quantity;

}
