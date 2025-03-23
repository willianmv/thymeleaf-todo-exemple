package com.example.thymeleaf_todo.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TASK")
@Data
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Este campo não pode ser vazio")
    @Size(min = 3, max = 100, message = "Tamanho deve ser de 3 a 100 caracteres")
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Este campo não pode ser vazio")
    @FutureOrPresent(message = "Datas passadas não permitidas")
    private LocalDate deadLine;

    private LocalDate finishedAt;

    public Task() {
        this.createdAt = LocalDateTime.now();
    }

    public void markAsFinished(){
        this.finishedAt = LocalDate.now();
    }

}
