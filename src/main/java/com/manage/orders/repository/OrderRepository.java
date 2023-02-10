package com.manage.orders.repository;

import com.manage.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByComplete(boolean b);

}
