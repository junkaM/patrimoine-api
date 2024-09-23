package com.hei.patrimoine_api.controller;


import com.hei.patrimoine_api.model.Patrimoine;
import com.hei.patrimoine_api.service.PatrimoineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

        private final PatrimoineService patrimoineService;

        public PatrimoineController(PatrimoineService patrimoineService) {
            this.patrimoineService = patrimoineService;
        }

        @PutMapping("/{id}")
        public ResponseEntity<Void> createOrUpdatePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) throws IOException {
            patrimoineService.saveOrUpdatePatrimoine(id, patrimoine);
            return ResponseEntity.ok().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Patrimoine> getPatrimoine(@PathVariable String id) throws IOException {
            Patrimoine patrimoine = patrimoineService.getPatrimoine(id);
            if (patrimoine != null) {
                return ResponseEntity.ok(patrimoine);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}
