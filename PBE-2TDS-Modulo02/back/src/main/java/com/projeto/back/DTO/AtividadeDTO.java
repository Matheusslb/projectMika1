package com.projeto.back.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AtividadeDTO {
    private Long id;
    private String numero;
    private String descricao;
    private ProfessorDTO professor;

    public AtividadeDTO(Long id, String numero, String descricao, ProfessorDTO professor) {
        this.id = id;
        this.numero = numero;
        this.descricao = descricao;
        this.professor = professor;
    }
}
