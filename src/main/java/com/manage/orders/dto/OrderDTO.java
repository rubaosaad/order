package com.manage.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.orders.domain.Item;
import com.manage.orders.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {

    private Long id;
    private Item item;
    private Integer quantity;
    @JsonIgnore
    private Date creationDate;
    private User user;
    private boolean complete;

}
