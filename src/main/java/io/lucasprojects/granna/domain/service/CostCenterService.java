package io.lucasprojects.granna.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import io.lucasprojects.granna.domain.exception.ResourceNotFoundException;
import io.lucasprojects.granna.domain.model.CostCenter;
import io.lucasprojects.granna.domain.model.User;
import io.lucasprojects.granna.domain.repository.CostCenterRepository;
import io.lucasprojects.granna.dto.CostCenter.CostCenterRequestDTO;
import io.lucasprojects.granna.dto.CostCenter.CostCenterResponseDTO;

public class CostCenterService implements ICRUDService<CostCenterRequestDTO, CostCenterResponseDTO> {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CostCenterResponseDTO> getAll() {
        List<CostCenter> list = costCenterRepository.findAll();
        return list.stream()
                .map(costCenter -> mapper.map(costCenter, CostCenterResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CostCenterResponseDTO getById(Long id) {
        Optional<CostCenter> optCostCenter = costCenterRepository.findById(id);

        if (optCostCenter.isEmpty())
            throw new ResourceNotFoundException("Unable to find CostCenter with id: " + id);

        return mapper.map(optCostCenter, CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO add(CostCenterRequestDTO dto) {
        CostCenter costCenter = mapper.map(dto, CostCenter.class);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        costCenter.setUser(user);
        costCenter.setId(null);

        costCenter = costCenterRepository.save(costCenter);

        return mapper.map(user, CostCenterResponseDTO.class);
    }

    @Override
    public CostCenterResponseDTO update(Long id, CostCenterRequestDTO dto) {
        getById(id);
        CostCenter costCenter = mapper.map(dto, CostCenter.class);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        costCenter.setUser(user);
        costCenter.setId(id);

        costCenter = costCenterRepository.save(costCenter);

        return mapper.map(user, CostCenterResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        costCenterRepository.deleteById(id);
    }

}
