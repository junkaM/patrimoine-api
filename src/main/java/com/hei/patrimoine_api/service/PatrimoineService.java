package com.hei.patrimoine_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hei.patrimoine_api.model.Patrimoine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PatrimoineService {
        private final Path storagePath = Paths.get("patrimoines");
        private final ObjectMapper objectMapper = new ObjectMapper();

        public PatrimoineService() throws IOException, IOException {
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }
        }

        public Patrimoine getPatrimoine(String id) throws IOException {
            Path filePath = storagePath.resolve(id + ".json");
            if (Files.exists(filePath)) {
                return objectMapper.readValue(filePath.toFile(), Patrimoine.class);
            } else {
                return null;
            }
        }

        public void saveOrUpdatePatrimoine(String id, Patrimoine patrimoine) throws IOException {
            patrimoine.setDerniereModification(LocalDateTime.now());
            Path filePath = storagePath.resolve(id + ".json");
            objectMapper.writeValue(filePath.toFile(), patrimoine);
        }
}
