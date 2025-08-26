package com.example.filiera.controller;

import com.example.filiera.dto.MapPointResponse;
import com.example.filiera.service.MappaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mappa")
public class MappaController {

    private final MappaService service;

    public MappaController(MappaService service) {
        this.service = service;
    }

    @GetMapping("/punti")
    public ResponseEntity<List<MapPointResponse>> punti() {
        return ResponseEntity.ok(service.punti());
    }
}
