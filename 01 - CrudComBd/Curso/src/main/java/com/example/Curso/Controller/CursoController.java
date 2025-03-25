package com.example.Curso.Controller;

import com.example.Curso.banco.CursoDb;
import com.example.Curso.model.Aluno;
import com.example.Curso.model.Curso;

import java.util.List;

public class CursoController {

    CursoDb repository = new CursoDb();

    public List<Curso> getAll(){
        return repository.findAll();
    }

    public List<Curso> getByProfessor(String nomeProfessor){
        return repository.findByProfessor(nomeProfessor);
    }

    public List<Curso> getBySala(int sala){
        return repository.findBySala(sala);
    }

    public Curso getById(int id){
        return repository.getById(id);
    }

    public boolean insertBanco(Curso curso){
        return repository.insert(curso);
    }

    public Curso insertAluno(int idCurso, Aluno aluno){
        return repository.insertAluno(idCurso, aluno);
    }

    // esta funcao fara o mesmo insert do aluno que a funcao a cima, porém com melhores práticas de programção
    public String insertAlunoMelhorado(int idCurso, Aluno aluno){
        Curso curso = repository.getById(idCurso);
        if(curso == null){
            return "Curso não encontrado, para que aluno possa ser inserido!";
        }

        boolean result = repository.insertAlunoMelhorado(curso, aluno);

        if(result){
            return "Aluno inserido com sucesso";
        }
        return "Não foi possível inserir alunos";
    }

    public Curso update(int id, Curso curso){
        boolean result = repository.update(id, curso);

        if(result){
            return curso;
        }
        return null;
    }

    public boolean updateAluno(int idCurso, int idAluno, Aluno aluno){
        return repository.updateAluno(idCurso, idAluno, aluno);
    }

    public boolean delete(int id){
        return repository.delete(id);
    }

}
