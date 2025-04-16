package com.projeto.back.service;

import com.projeto.back.DTO.ProfessorDTO;
import com.projeto.back.DTO.TurmaDTO;
import com.projeto.back.entity.Professor;
import com.projeto.back.entity.Turma;
import com.projeto.back.repository.ProfessorRepository;
import com.projeto.back.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public TurmaDTO criarTurma(Turma turma) {
        // Encontrar o professor pela ID e associar à turma
        Professor professor = professorRepository.findById(turma.getProfessor().getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        turma.setProfessor(professor);

        Turma turmaSalva = turmaRepository.save(turma);
        return new TurmaDTO(turmaSalva.getId(), turmaSalva.getNumero(), turmaSalva.getNome(),
                new ProfessorDTO(turmaSalva.getProfessor().getId(),
                        turmaSalva.getProfessor().getNome(),
                        turmaSalva.getProfessor().getEmail(),
                        turmaSalva.getProfessor().getSenha()));
    }

    public TurmaDTO atualizarTurma(Long id, Turma turmaDetails) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isEmpty()) {
            throw new RuntimeException("Turma não encontrada");
        }

        Turma turma = turmaOptional.get();
        turma.setNumero(turmaDetails.getNumero());
        turma.setNome(turmaDetails.getNome());

        // Atualizar o professor associado à turma
        Professor professor = professorRepository.findById(turmaDetails.getProfessor().getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        turma.setProfessor(professor);

        Turma turmaAtualizada = turmaRepository.save(turma);
        return new TurmaDTO(turmaAtualizada.getId(), turmaAtualizada.getNumero(), turmaAtualizada.getNome(),
                new ProfessorDTO(turmaAtualizada.getProfessor().getId(),
                        turmaAtualizada.getProfessor().getNome(),
                        turmaAtualizada.getProfessor().getEmail(),
                        turmaAtualizada.getProfessor().getSenha()));
    }

    public void deletarTurma(Long id) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isEmpty()) {
            throw new RuntimeException("Turma não encontrada");
        }

        turmaRepository.delete(turmaOptional.get());
    }

    public List<TurmaDTO> obterTodasTurmas() {
        return turmaRepository.findAll()
                .stream()
                .map(turma -> new TurmaDTO(turma.getId(), turma.getNumero(), turma.getNome(),
                        new ProfessorDTO(turma.getProfessor().getId(),
                                turma.getProfessor().getNome(),
                                turma.getProfessor().getEmail(),
                                turma.getProfessor().getSenha())))
                .collect(Collectors.toList());
    }

    public TurmaDTO obterTurmaPorId(Long id) {
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if (turmaOptional.isEmpty()) {
            throw new RuntimeException("Turma não encontrada");
        }

        Turma turma = turmaOptional.get();
        return new TurmaDTO(turma.getId(), turma.getNumero(), turma.getNome(),
                new ProfessorDTO(turma.getProfessor().getId(),
                        turma.getProfessor().getNome(),
                        turma.getProfessor().getEmail(),
                        turma.getProfessor().getSenha()));
    }
}
