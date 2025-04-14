package com.projeto.back.controller;

import com.projeto.back.DTO.AtividadeDTO;
import com.projeto.back.entity.Atividade;
import com.projeto.back.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public AtividadeDTO criarAtividade(@RequestBody Atividade atividade) {
        return atividadeService.criarAtividade(atividade);
    }

    @PutMapping("/{id}")
    public AtividadeDTO atualizarAtividade(@PathVariable Long id, @RequestBody Atividade atividade) {
        return atividadeService.atualizarAtividade(id, atividade);
    }

    @DeleteMapping("/{id}")
    public void deletarAtividade(@PathVariable Long id) {
        atividadeService.deletarAtividade(id);
    }

    @GetMapping
    public List<AtividadeDTO> obterTodasAtividades() {
        return atividadeService.obterTodasAtividades();
    }

    @GetMapping("/{id}")
    public AtividadeDTO obterAtividadePorId(@PathVariable Long id) {
        return atividadeService.obterAtividadePorId(id);
    }
}
