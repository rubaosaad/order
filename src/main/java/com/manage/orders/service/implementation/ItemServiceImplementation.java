package com.manage.orders.service.implementation;

import com.manage.orders.domain.Item;
import com.manage.orders.dto.ItemDTO;
import com.manage.orders.repository.ItemRepository;
import com.manage.orders.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImplementation implements ItemService {

	private final ItemRepository repository;

	private final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public ItemServiceImplementation(ItemRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ItemDTO> getAll() {
		return repository.findAll().stream().map(user -> modelMapper.map(user, ItemDTO.class))
				.collect(Collectors.toList());
	}
	@Override
	public ItemDTO getItem(Long id) {
		return modelMapper.map(repository.getReferenceById(id), ItemDTO.class);
	}

	@Override
	public ItemDTO updateItem(ItemDTO itemDTO) throws Exception {

		try {
			Item entity = repository.getReferenceById(itemDTO.getId());
			entity.setName(itemDTO.getName());
			repository.save(entity);
		} catch (Exception e) {
			throw new Exception("Not found!");
		}

		return itemDTO;
	}

	@Override
	public ItemDTO addItem(ItemDTO itemDTO) throws Exception {
		if ("".equals(itemDTO.getName())) {
			throw new Exception("Name is mandatory.");
		}
		Item entity = modelMapper.map(itemDTO, Item.class);
		repository.save(entity);
		return itemDTO;
	}
	@Override
	public void deleteItem(Long id)  throws Exception{
		try {
			repository.delete(new Item(id));
		} catch (Exception e) {
			throw new Exception("Not found!");
		}
	}

}
