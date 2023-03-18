package io.lucasprojects.granna.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.lucasprojects.granna.domain.exception.ResourceBadRequestException;
import io.lucasprojects.granna.domain.exception.ResourceNotFoundException;
import io.lucasprojects.granna.domain.model.User;
import io.lucasprojects.granna.domain.repository.UserRepository;
import io.lucasprojects.granna.dto.User.UserRequestDTO;
import io.lucasprojects.granna.dto.User.UserResponseDTO;

@Service
public class UserService implements ICRUDService<UserRequestDTO, UserResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UserResponseDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(user -> mapper.map(users, UserResponseDTO.class))
                    .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new ResourceNotFoundException("Unable to find user with id: " + id);

        return mapper.map(user.get(), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO add(UserRequestDTO dto) {
        validateUser(dto);

        User user = mapper.map(dto, User.class);
        user = userRepository.save(user);

        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        UserResponseDTO userFound = getById(id);
        validateUser(dto);

        User user = mapper.map(dto, User.class);

        user.setId(id);
        user.setInativationDate(userFound.getInativationDate());

        user = userRepository.save(user);
        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        UserResponseDTO userFound = getById(id);
        User user = mapper.map(userFound, User.class);

        user.setInativationDate(new Date());

        userRepository.save(user);
    }

    private void validateUser(UserRequestDTO dto) {
        if(dto.getEmail() == null || dto.getPassword() == null)
            throw new ResourceBadRequestException("Email or password is null.");
    }
    
}
