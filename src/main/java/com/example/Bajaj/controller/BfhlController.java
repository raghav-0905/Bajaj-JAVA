package com.example.Bajaj.controller;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;
import com.example.Bajaj.service.BfhlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @GetMapping
    public ResponseEntity<?> getOperationCode() {
        return ResponseEntity.ok(java.util.Map.of("operation_code", 1));
    }

    @PostMapping
    public ResponseEntity<BfhlResponse> process(@RequestBody BfhlRequest request) {
        return ResponseEntity.ok(bfhlService.processData(request));
    }
}
