package com.projeto.back.service;

import com.projeto.back.DTO.AtividadeDTO;
import com.projeto.back.DTO.ProfessorDTO;
import com.projeto.back.entity.Atividade;
import com.projeto.back.entity.Professor;
import com.projeto.back.repository.AtividadeRepository;
import com.projeto.back.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public AtividadeDTO criarAtividade(Atividade atividade) {
        // Encontrar o professor pela ID e associar à atividade
        Professor professor = professorRepository.findById(atividade.getProfessor().getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        atividade.setProfessor(professor);

        Atividade atividadeSalva = atividadeRepository.save(atividade);
        return new AtividadeDTO(atividadeSalva.getId(), atividadeSalva.getNumero(), atividadeSalva.getDescricao(),
                new ProfessorDTO(atividadeSalva.getProfessor().getId(),
                        atividadeSalva.getProfessor().getNome(),
                        atividadeSalva.getProfessor().getEmail(),
                        atividadeSalva.getProfessor().getSenha()));
    }

    public AtividadeDTO atualizarAtividade(Long id, Atividade atividadeDetails) {
        Optional<Atividade> atividadeOptional = atividadeRepository.findById(id);
        if (atividadeOptional.isEmpty()) {
            throw new RuntimeException("Atividade não encontrada");
        }

        Atividade atividade = atividadeOptional.get();
        atividade.setNumero(atividadeDetails.getNumero());
        atividade.setDescricao(atividadeDetails.getDescricao());

        // Atualizar o professor associado à atividade
        Professor professor = professorRepository.findById(atividadeDetails.getProfessor().getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        atividade.setProfessor(professor);

        Atividade atividadeAtualizada = atividadeRepository.save(atividade);
        return new AtividadeDTO(atividadeAtualizada.getId(), atividadeAtualizada.getNumero(), atividadeAtualizada.getDescricao(),
                new ProfessorDTO(atividadeAtualizada.getProfessor().getId(),
                        atividadeAtualizada.getProfessor().getNome(),
                        atividadeAtualizada.getProfessor().getEmail(),
                        atividadeAtualizada.getProfessor().getSenha()));
    }

    public void deletarAtividade(Long id) {
        Optional<Atividade> atividadeOptional = atividadeRepository.findById(id);
        if (atividadeOptional.isEmpty()) {
            throw new RuntimeException("Atividade não encontrada");
        }

        atividadeRepository.delete(atividadeOptional.get());
    }

    public List<AtividadeDTO> obterTodasAtividades() {
        return atividadeRepository.findAll()
                .stream()
                .map(atividade -> new AtividadeDTO(atividade.getId(), atividade.getNumero(), atividade.getDescricao(),
                        new ProfessorDTO(atividade.getProfessor().getId(),
                                atividade.getProfessor().getNome(),
                                atividade.getProfessor().getEmail(),
                                atividade.getProfessor().getSenha())))
                .collect(Collectors.toList());
    }

    public AtividadeDTO obterAtividadePorId(Long id) {
        Optional<Atividade> atividadeOptional = atividadeRepository.findById(id);
        if (atividadeOptional.isEmpty()) {
            throw new RuntimeException("Atividade não encontrada");
        }

        Atividade atividade = atividadeOptional.get();
        return new AtividadeDTO(atividade.getId(), atividade.getNumero(), atividade.getDescricao(),
                new ProfessorDTO(atividade.getProfessor().getId(),
                        atividade.getProfessor().getNome(),
                        atividade.getProfessor().getEmail(),
                        atividade.getProfessor().getSenha()));
    }
}