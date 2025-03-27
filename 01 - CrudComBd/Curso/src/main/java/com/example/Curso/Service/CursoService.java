package com.example.Curso.Service;

import com.example.Curso.DTO.CursoDTO;
import com.example.Curso.Entity.Curso;
import com.example.Curso.Repository.CursoRepository;
import com.example.Curso.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public Curso fromDTO(CursoDTO cursoDTO){
        Curso curso = new Curso();
        curso.setNome(cursoDTO.getNome());
        curso.setNumeroSala(cursoDTO.getNumeroSala());
        curso.setProfessor(professorRepository.findById(cursoDTO.getIdProfessor()).get());

        return curso;
    }

    public Curso saveDto(CursoDTO cursoDTO){
        Curso curso = this.fromDTO(cursoDTO);
        Curso cursoBd = cursoRepository.save(curso);
        return cursoBd;
    }
}
