package com.hei.patrimoine_api.model;

import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patrimoine {
    private String possesseur;
    private LocalDateTime derniereModification;
}
