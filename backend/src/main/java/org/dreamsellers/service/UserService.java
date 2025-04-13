package org.dreamsellers.service;

import org.dreamsellers.dto.UserDTO;
import org.dreamsellers.model.UserEntity;
import org.dreamsellers.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  public UserDTO getUser(long id) {
    UserEntity user = userRepository.getUserById(id);
    return modelMapper.map(user, UserDTO.class);
  }

}
