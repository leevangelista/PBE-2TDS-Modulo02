package com.example.Curso.Service;

import com.example.Curso.DTO.AlunoDTO;
import com.example.Curso.DTO.CursoDTORequest;
import com.example.Curso.DTO.CursoDTOResponse;
import com.example.Curso.DTO.ProfessorDTO;
import com.example.Curso.Entity.Aluno;
import com.example.Curso.Entity.Curso;
import com.example.Curso.Entity.Professor;
import com.example.Curso.Repository.CursoRepository;
import com.example.Curso.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // teremos dois objetos DTO para ser trabalhado devido
    // a entrada das informações de alunos não serem feitas pelo Curso, ou seja o CursoDTORequest nao tem o aluno
    // no caso da saida de dados, ao retornar um curso será retornado o seus alunos, assim será utilizado CursoDTOResponse

    // converte de CursoDTORequest para Curso
    public Curso fromDTO(CursoDTORequest cursoDTORequest){
        Curso curso = new Curso();
        curso.setNome(cursoDTORequest.getNome());
        curso.setNumeroSala(cursoDTORequest.getNumeroSala());
        curso.setProfessor(cursoDTORequest.getProfessor());

        return curso;
    }

    // converte de Curso para CursoDTO response
    public CursoDTOResponse toDTO(Curso curso){
        CursoDTOResponse cursoDTOResponse = new CursoDTOResponse();
        cursoDTOResponse.setId(curso.getIdCurso());
        cursoDTOResponse.setNome(curso.getNome());
        cursoDTOResponse.setNumeroSala(curso.getNumeroSala());
        cursoDTOResponse.setProfessor(curso.getProfessor());
        cursoDTOResponse.setAlunos(curso.getAlunos());

        return cursoDTOResponse;
    }

    public List<Curso> getAll(){
        return cursoRepository.findAll();
    }

    public Optional<CursoDTOResponse> getById(Long id){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if(optionalCurso.isPresent()){// verifica se encontrou algum professor
            return Optional.of(this.toDTO(optionalCurso.get()));
        }else {
            return Optional.empty(); // um objeto Optional vazio.
        }
//        return professorRepository.findById(id).map(this::toDTO);
    }

    public CursoDTOResponse saveDto(CursoDTORequest cursoDTORequest){
        Curso curso = this.fromDTO(cursoDTORequest);
        Curso cursoBd = cursoRepository.save(curso);
        return this.toDTO(cursoBd);
    }

    public Optional<CursoDTOResponse> updateCurso(Long id, CursoDTORequest cursoDTORequest){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if(optionalCurso.isPresent()){
            Curso curso = optionalCurso.get();
            curso.setNome(cursoDTORequest.getNome());
            curso.setNumeroSala(cursoDTORequest.getNumeroSala());
            curso.setProfessor(cursoDTORequest.getProfessor());

            Curso cursoUpdate = cursoRepository.save(curso);

            return Optional.of(this.toDTO(cursoUpdate));
        }else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(cursoRepository.existsById(id)){
            cursoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
