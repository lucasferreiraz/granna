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

import io.lucasprojects.granna.domain.service.CostCenterService;
import io.lucasprojects.granna.dto.CostCenter.CostCenterRequestDTO;
import io.lucasprojects.granna.dto.CostCenter.CostCenterResponseDTO;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/costcenter")
public class CostCenterController {
    
    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    public ResponseEntity<List<CostCenterResponseDTO>> getAll() {
        return ResponseEntity.ok(costCenterService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(costCenterService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CostCenterResponseDTO> add(@RequestBody CostCenterRequestDTO dto) {
        CostCenterResponseDTO responseDTO = costCenterService.add(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> update(@PathVariable Long id, @RequestBody CostCenterRequestDTO dto) {
        return ResponseEntity.ok(costCenterService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        costCenterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
