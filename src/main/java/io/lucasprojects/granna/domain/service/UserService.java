package io.lucasprojects.granna.domain.service;

import java.util.List;

import io.lucasprojects.granna.dto.User.UserRequestDTO;
import io.lucasprojects.granna.dto.User.UserResponseDTO;

public class UserService implements ICRUDService<UserRequestDTO, UserResponseDTO> {

    @Override
    public List<UserResponseDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public UserResponseDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public UserResponseDTO add(UserRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
