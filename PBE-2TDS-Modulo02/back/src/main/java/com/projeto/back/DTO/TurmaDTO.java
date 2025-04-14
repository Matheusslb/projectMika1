package com.projeto.back.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projeto.back.DTO.ProfessorDTO;
import com.projeto.back.entity.Professor;
import lombok.Data;

@Data
public class TurmaDTO {
    private Long id;
    private String numero;
    private String nome;
    private ProfessorDTO professor;

    public TurmaDTO(Long id, String numero, String nome, ProfessorDTO professorDTO) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
    }



}
