package com.example.Curso.banco;

import com.example.Curso.model.Aluno;
import com.example.Curso.model.Curso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CursoDb {
    public List<Curso> cursos;

    public CursoDb(){
        this.cursos = new ArrayList<>();
    }
    // busca todos os cursos ordenando pelo nome
    public List<Curso> findAll(){
        return cursos.stream()
                .sorted(Comparator.comparing(Curso::getNome).reversed())
                .toList();
    }

    // busca o curso com base no professor
    public List<Curso> findByProfessor(String nomeProfessor){
        return cursos.stream()
                .filter(curso -> curso.getProfessor().getNome().equals(nomeProfessor))
                .toList();
    }

    // busca o curso com base numero da sala;
    public List<Curso> findBySala(int sala){
        return cursos.stream()
                .filter(curso -> curso.getNumeroSala() == sala)
                .toList();
    }

    // busca um curso
    public Curso getById(int id){
        return cursos.stream()
                .filter(curso -> curso.getIdCurso() == id)
                .findFirst()
                .orElse(null);
    }

    // insere o funcionário
    public boolean insert(Curso curso){
        cursos.add(curso);
        return true;
    }

    // insert de aluno novo no curso
    public Curso insertAluno(int idCurso, Aluno aluno){
        Curso cursoBd = cursos.stream()
                .filter(c -> c.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);

        if (cursoBd == null){
            return null;
        }

        cursoBd.getAlunos().add(aluno);

        return cursoBd;
    }

    // insert de aluno novo no curso, com melhores práticas de programação
    public Boolean insertAlunoMelhorado(Curso curso, Aluno aluno){
        curso.getAlunos().add(aluno);
        return true;
    }

    // atualiza o funcionário com base no id
    public boolean update(int id, Curso curso){
        Curso cursoBd = cursos.stream()
                .filter(c -> c.getIdCurso() == id)
                .findFirst()
                .orElse(null);

        if (cursoBd == null){
            return false;
        }

        cursoBd.setNome(curso.getNome());
        cursoBd.setNumeroSala(curso.getNumeroSala());
        cursoBd.setProfessor(curso.getProfessor());

        return true;
    }

    public boolean updateAluno(int idCurso, int idAluno, Aluno aluno){
        Curso cursoBd = cursos.stream()
                .filter(curso -> curso.getIdCurso() == idCurso)
                .findFirst()
                .orElse(null);

        if (cursoBd == null){
            return false;
        }

        Aluno alunoBd = cursoBd.getAlunos().stream()
                .filter(a -> a.getIdAluno() == idAluno)
                .findFirst()
                .orElse(null);

        if(cursoBd == null){
            return false;
        }

        alunoBd.setNome(aluno.getNome());
        alunoBd.setCpf(aluno.getCpf());

        return true;

    }

    // deleta um usuário
    public boolean delete(int id){
        Curso cursoBd = cursos.stream()
                .filter(c -> c.getIdCurso() == id)
                .findFirst()
                .orElse(null);

        cursos.remove(cursoBd);

        return true;
    }
}
