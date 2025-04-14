package com.projeto.back.controller;


import com.projeto.back.DTO.TurmaDTO;
import com.projeto.back.entity.Turma;
import com.projeto.back.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public TurmaDTO criarTurma(@RequestBody Turma turma) {
        return turmaService.criarTurma(turma);
    }

    @PutMapping("/{id}")
    public TurmaDTO atualizarTurma(@PathVariable Long id, @RequestBody Turma turma) {
        return turmaService.atualizarTurma(id, turma);
    }

    @DeleteMapping("/{id}")
    public void deletarTurma(@PathVariable Long id) {
        turmaService.deletarTurma(id);
    }

    @GetMapping
    public List<TurmaDTO> obterTodasTurmas() {
        return turmaService.obterTodasTurmas();
    }

    @GetMapping("/{id}")
    public TurmaDTO obterTurmaPorId(@PathVariable Long id) {
        return turmaService.obterTurmaPorId(id);
    }
}
