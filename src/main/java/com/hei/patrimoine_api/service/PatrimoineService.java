package com.hei.patrimoine_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hei.patrimoine_api.model.Patrimoine;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class PatrimoineService {

    private final Path storagePath = Paths.get("patrimoines");
    private final ObjectMapper objectMapper;

    public PatrimoineService() throws IOException {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

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
