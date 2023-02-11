package com.manage.orders.service.implementation;

import com.manage.orders.domain.StockMovement;
import com.manage.orders.dto.StockMovementDTO;
import com.manage.orders.repository.StockRepository;
import com.manage.orders.service.OrderService;
import com.manage.orders.service.StockService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImplementation implements StockService {

    private final StockRepository repository;

    @Autowired
    private OrderService orderService;

    private ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LogManager.getLogger(StockServiceImplementation.class);

    @Autowired
    public StockServiceImplementation(StockRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StockMovementDTO> getAll() {
        return repository.findAll().stream().map(stock -> modelMapper.map(stock, StockMovementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StockMovementDTO addStock(StockMovementDTO stockMovementDTO) throws Exception {
        Optional<StockMovement> result = findByItemId(stockMovementDTO.getItem().getId());
        StockMovement entity = modelMapper.map(stockMovementDTO, StockMovement.class);
        if(result.isPresent()){
            entity.setQuantity(stockMovementDTO.getQuantity());
            entity.setId(stockMovementDTO.getId());
        };
        entity.setCreationDate(stockMovementDTO.getCreationDate());
        repository.save(entity);
        return stockMovementDTO;
    }


    @Override
    public StockMovementDTO getStock(Long id) {
        return modelMapper.map(repository.getReferenceById(id), StockMovementDTO.class);
    }

    @Override
    public StockMovementDTO updateStock(StockMovementDTO stockMovementDTO) {
        StockMovement entity = modelMapper.map(stockMovementDTO, StockMovement.class);
        repository.save(entity);
        return stockMovementDTO;
    }

    @Override
    public void deleteStock(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void updateItemsStock(Long id, Integer quantity) {
        Optional<StockMovement> result = repository.findByItemId(id);
        if (result.isPresent()) {
            StockMovement entity = result.get();
            Integer res = entity.getQuantity() - quantity;
            entity.setQuantity(res);
            repository.save(entity);
        }

    }

    @Override
    public Optional<StockMovement> findByItemId(Long id) {
        return repository.findByItemId(id);
    }

}
