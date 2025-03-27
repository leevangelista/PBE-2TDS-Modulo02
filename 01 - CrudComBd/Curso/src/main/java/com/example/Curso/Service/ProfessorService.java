package com.example.Curso.Service;

import com.example.Curso.DTO.ProfessorDTO;
import com.example.Curso.Entity.Professor;
import com.example.Curso.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor fromDTO(ProfessorDTO professorDTO){
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professor.getCpf());

        return professor;
    }

    public Professor save(ProfessorDTO professorDTO){
        Professor professor = this.fromDTO(professorDTO);
        Professor professorBd = professorRepository.save(professor);
        return professorBd;
    }
}
