package com.projeto.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "professor_id")  // Esta coluna é a chave estrangeira

    private Professor professor;
}
