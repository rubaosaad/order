package com.manage.orders.service;

import com.manage.orders.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

	ItemDTO getItem(Long id);
	ItemDTO addItem(ItemDTO itemDTO) throws Exception;
	List<ItemDTO> getAll();
	ItemDTO updateItem(ItemDTO itemDTO) throws Exception;
	void deleteItem(Long id) throws Exception;

}
