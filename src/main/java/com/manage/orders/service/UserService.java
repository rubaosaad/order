package com.manage.orders.service;

import com.manage.orders.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	List<UserDTO> getAll();

	UserDTO addUser(UserDTO userDTO);

	UserDTO getUser(Long id);

	UserDTO updateUser(UserDTO userDTO);

	void deleteUser(Long id);

}
