package com.manage.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name="item_id")
	private Item item;
	private Integer quantity;

	@JsonIgnore
	private Date creationDate;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private boolean complete;

	@OneToOne
	@JoinColumn(name = "stock_id")
	private StockMovement stock;
	
	public Order(Long id) {
		this.id = id;
	}

}
