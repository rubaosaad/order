package com.manage.orders.service;

import com.manage.orders.dto.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

	List<OrderDTO> getAll();

	OrderDTO addOrder(OrderDTO orderDTO) throws Exception;

	OrderDTO getOrder(Long id) throws Exception;

	OrderDTO updateOrder(OrderDTO orderDTO) throws Exception;

	void deleteOrder(Long id) throws Exception;


}
