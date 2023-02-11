package com.manage.orders.service.implementation;

import com.manage.orders.config.Email;
import com.manage.orders.domain.Order;
import com.manage.orders.domain.StockMovement;
import com.manage.orders.dto.OrderDTO;
import com.manage.orders.repository.OrderRepository;
import com.manage.orders.service.OrderService;
import com.manage.orders.service.StockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

	private final OrderRepository repository;

	@Autowired
	private StockService stockService;

	@Autowired
	private Email email;

	private final ModelMapper modelMapper = new ModelMapper();

	private static final Logger logger = LogManager.getLogger(OrderServiceImplementation.class);

	@Autowired
	public OrderServiceImplementation(final OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<OrderDTO> getAll() {
		return repository.findAll().stream().map(order -> modelMapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	public OrderDTO addOrder(OrderDTO orderDTO) throws Exception {

		Order entity = modelMapper.map(orderDTO, Order.class);
		entity.setCreationDate(new Date());

		StockMovement stock = getQuantityItems(orderDTO);

		if (stock != null && (stock.getQuantity() >= orderDTO.getQuantity())) entity.setComplete(true);

		repository.saveAndFlush(entity);

		if (entity.isComplete()) {
			try {
				sendEmail(entity);
			}catch(Exception e) {
				logger.error("Error to send email.");
			}
			stockService.updateItemsStock(orderDTO.getItem().getId(), orderDTO.getQuantity());
			stockService.getAll();
		}

		return orderDTO;
	}

	private StockMovement getQuantityItems(OrderDTO orderDTO) {
		Optional<StockMovement> entity = stockService.findByItemId(orderDTO.getItem().getId());
		if (entity.isPresent()) {
			return entity.get();
		} else {
			return null;
		}
	}

	@Override
	public OrderDTO getOrder(Long id) {
		return modelMapper.map(repository.getReferenceById(id), OrderDTO.class);
	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDTO) throws Exception {

		Order entity = modelMapper.map(orderDTO, Order.class);
		repository.save(entity);
		return orderDTO;
	}
	
	@Override
	public void deleteOrder(Long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("Not found!");
		}
	}


	private void sendEmail(Order order) {
		email.send(order.getUser().getEmail(), "Order Complete", "Thank you for the order");
	}

}
