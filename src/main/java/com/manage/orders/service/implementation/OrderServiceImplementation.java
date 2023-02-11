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
			logger.info("order complete!");
			try {
				sendEmail(entity);
				logger.info("email sent!");
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
		try {
			Order entity = modelMapper.map(orderDTO, Order.class);
			repository.save(entity);

		} catch (Exception e) {
			throw new Exception("Not found!");
		}
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

	@Override
	public void processIncompletedOrders() {
		List<Order> incompletedOrdersList = repository.findAllByComplete(false);
		incompletedOrdersList.forEach((order) -> {
			Optional<StockMovement> stock = stockService.findByItemId(order.getItem().getId());
			stock.ifPresent(stck -> {
				if (stck.getQuantity() >= order.getQuantity()) {
					Order orderPersist = new Order();
					orderPersist.setId(order.getId());
					orderPersist.setComplete(true);
					orderPersist.setStock(new StockMovement(stck.getId()));
					orderPersist.setItem(order.getItem());
					orderPersist.setQuantity(order.getQuantity());
					orderPersist.setCreationDate(order.getCreationDate());
					orderPersist.setUser(order.getUser());
					repository.save(orderPersist);
					try {
						sendEmail(order);
					} catch (Exception e) {
						logger.error("Error email send");
					}
					stockService.updateItemsStock(orderPersist.getItem().getId(), orderPersist.getQuantity());
				}
			});
		});

	}

	private void sendEmail(Order order) {
		email.send(order.getUser().getEmail(), "Order Complete", "Thank you for the order");
	}

}
