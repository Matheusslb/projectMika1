package com.projeto.back.controller;

import com.projeto.back.DTO.ProfessorDTO;
import com.projeto.back.entity.Professor;
import com.projeto.back.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ProfessorDTO criarProfessor(@RequestBody Professor professor) {
        return professorService.criarProfessor(professor);
    }

    @PutMapping("/{id}")
    public ProfessorDTO atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        return professorService.atualizarProfessor(id, professor);
    }

    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);
    }

    @GetMapping
    public List<ProfessorDTO> obterTodosProfessores() {
        return professorService.obterTodosProfessores();
    }

    @GetMapping("/{id}")
    public ProfessorDTO obterProfessorPorId(@PathVariable Long id) {
        return professorService.obterProfessorPorId(id);
    }

    @GetMapping("/email/{email}")
    public ProfessorDTO obterProfessorPorEmail(@PathVariable String email) {
        return professorService.obterProfessorPorEmail(email);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ProfessorDTO loginDTO) {
        ProfessorDTO professor = professorService.obterProfessorPorEmail(loginDTO.getEmail());

        if (professor == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Email não encontrado"
            ));
        }

        if (!professor.getSenha().equals(loginDTO.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "success", false,
                    "message", "Senha incorreta"
            ));
        }

        return ResponseEntity.ok(Map.of("success", true));
    }




}
