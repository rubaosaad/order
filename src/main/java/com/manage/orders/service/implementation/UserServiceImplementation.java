package com.manage.orders.service.implementation;

import com.manage.orders.domain.User;
import com.manage.orders.dto.UserDTO;
import com.manage.orders.repository.UserRepository;
import com.manage.orders.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImplementation(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {
        User entity = modelMapper.map(userDTO, User.class);
        repository.save(entity);
        return userDTO;
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User entity = modelMapper.map(userDTO, User.class);
        repository.save(entity);
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }


    @Override
    public UserDTO getUser(Long id) {
        return modelMapper.map(repository.getReferenceById(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}
