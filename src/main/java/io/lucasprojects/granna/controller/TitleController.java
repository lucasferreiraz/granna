package io.lucasprojects.granna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.lucasprojects.granna.domain.service.TitleService;
import io.lucasprojects.granna.dto.Title.TitleRequestDTO;
import io.lucasprojects.granna.dto.Title.TitleResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/title")
@Tag(name = "Title")
public class TitleController {
    
    @Autowired
    private TitleService titleService;

    @GetMapping
    public ResponseEntity<List<TitleResponseDTO>> getAll() {
        return ResponseEntity.ok(titleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TitleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(titleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TitleResponseDTO> add(@RequestBody TitleRequestDTO dto) {
        TitleResponseDTO responseDTO = titleService.add(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TitleResponseDTO> update(@PathVariable Long id, @RequestBody TitleRequestDTO dto) {
        return ResponseEntity.ok(titleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        titleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
