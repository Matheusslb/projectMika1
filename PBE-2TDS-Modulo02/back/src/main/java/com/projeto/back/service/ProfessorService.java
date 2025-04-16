package com.projeto.back.service;

import com.projeto.back.DTO.ProfessorDTO;
import com.projeto.back.entity.Professor;
import com.projeto.back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorDTO criarProfessor(Professor professor) {
        Professor professorSalvo = professorRepository.save(professor);
        return new ProfessorDTO(professorSalvo.getId(), professorSalvo.getNome(), professorSalvo.getEmail(), professorSalvo.getSenha());
    }

    public ProfessorDTO atualizarProfessor(Long id, Professor professorDetails) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            throw new RuntimeException("Professor não encontrado");
        }

        Professor professor = professorOptional.get();
        professor.setNome(professorDetails.getNome());
        professor.setEmail(professorDetails.getEmail());
        professor.setSenha(professorDetails.getSenha());

        Professor professorAtualizado = professorRepository.save(professor);
        return new ProfessorDTO(professorAtualizado.getId(), professorAtualizado.getNome(), professorAtualizado.getEmail(), professorAtualizado.getSenha());
    }

    public void deletarProfessor(Long id) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            throw new RuntimeException("Professor não encontrado");
        }

        professorRepository.delete(professorOptional.get());
    }

    public List<ProfessorDTO> obterTodosProfessores() {
        return professorRepository.findAll()
                .stream()
                .map(professor -> new ProfessorDTO(professor.getId(), professor.getNome(), professor.getEmail(), professor.getSenha()))
                .collect(Collectors.toList());
    }

    public ProfessorDTO obterProfessorPorId(Long id) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if (professorOptional.isEmpty()) {
            throw new RuntimeException("Professor não encontrado");
        }

        Professor professor = professorOptional.get();
        return new ProfessorDTO(professor.getId(), professor.getNome(), professor.getEmail(), professor.getSenha());
    }

    public ProfessorDTO obterProfessorPorEmail(String email) {
        Optional<Professor> professorOptional = professorRepository.findByEmail(email);
        if (professorOptional.isEmpty()) {
            throw new RuntimeException("Professor não encontrado com o email: " + email);
        }

        Professor professor = professorOptional.get();
        return new ProfessorDTO(professor.getId(), professor.getNome(), professor.getEmail(), professor.getSenha());
    }

    public Professor buscarPorEmail(String email) {
        return professorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
}
