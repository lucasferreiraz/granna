package io.lucasprojects.granna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.lucasprojects.granna.domain.service.DashBoardService;
import io.lucasprojects.granna.dto.DashBoard.DashBoardResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard")
public class DashBoardController {

    @Autowired
    private DashBoardService dashBoardService;
    
    @GetMapping
    public ResponseEntity<DashBoardResponseDTO> getFlowCash(
        @RequestParam(name = "start") String start,
        @RequestParam(name = "end") String end
    ) {
        return ResponseEntity.ok(dashBoardService.getFlowCash(start, end));
    }
}
