package com.example.prueba_applab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String description;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(
        regexp = "^(pendiente|progreso|completada)$",
        message = "El estado debe ser pendiente, en progreso o completado"
    )
    private String status;
}
