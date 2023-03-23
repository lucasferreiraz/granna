package io.lucasprojects.granna.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.lucasprojects.granna.domain.exception.ResourceBadRequestException;
import io.lucasprojects.granna.domain.exception.ResourceNotFoundException;
import io.lucasprojects.granna.domain.model.Title;
import io.lucasprojects.granna.domain.model.User;
import io.lucasprojects.granna.domain.repository.TitleRepository;
import io.lucasprojects.granna.dto.Title.TitleRequestDTO;
import io.lucasprojects.granna.dto.Title.TitleResponseDTO;

@Service
public class TitleService implements ICRUDService<TitleRequestDTO, TitleResponseDTO> {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<TitleResponseDTO> getAll() {
        List<Title> list = titleRepository.findAll();
        return list.stream()
                .map(title -> mapper.map(title, TitleResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TitleResponseDTO getById(Long id) {
        Optional<Title> optTitle = titleRepository.findById(id);

        if (optTitle.isEmpty())
            throw new ResourceNotFoundException("Unable to find Title with id: " + id);

        return mapper.map(optTitle.get(), TitleResponseDTO.class);
    }

    @Override
    public TitleResponseDTO add(TitleRequestDTO dto) {
        validateTitle(dto);
        Title title = mapper.map(dto, Title.class);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        title.setUser(user);
        title.setId(null);
        title.setRegisterDate(new Date());
        title = titleRepository.save(title);

        return mapper.map(title, TitleResponseDTO.class);
    }

    @Override
    public TitleResponseDTO update(Long id, TitleRequestDTO dto) {
        getById(id);
        validateTitle(dto);
        Title title = mapper.map(dto, Title.class);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        title.setUser(user);
        title.setId(id);

        title = titleRepository.save(title);

        return mapper.map(title, TitleResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        getById(id);
        titleRepository.deleteById(id);
    }

    public List<TitleResponseDTO> getFlowCash(String start, String end) {
        List<Title> list = titleRepository.getCashFlowByDueDate(start, end);

        return list.stream()
                .map(title -> mapper.map(title, TitleResponseDTO.class))
                .collect(Collectors.toList());
    }

    public void validateTitle(TitleRequestDTO dto) {
        boolean hasNoType = dto.getTitleType() == null;
        boolean hasNoExpirationDate = dto.getDueDate() == null;
        boolean hasNoValue = dto.getValue() == null;
        boolean hasNoDescription = dto.getDescription() == null;

        if (hasNoDescription || hasNoExpirationDate || hasNoType || hasNoValue) {
            throw new ResourceBadRequestException(
                    "The fields: type, expiration date, value and description cannot be  null");
        }
    }

}
