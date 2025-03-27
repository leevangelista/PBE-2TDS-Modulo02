package com.example.Curso.Service;

import com.example.Curso.DTO.AlunoDTO;
import com.example.Curso.Entity.Aluno;
import com.example.Curso.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;


    public Aluno fromDTO(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setCpf(alunoDTO.getCpf());
        aluno.setCurso(alunoDTO.getCurso());

        return aluno;
    }

    public AlunoDTO toDTO(Aluno aluno){
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getIdAluno());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setCurso(aluno.getCurso());

        return alunoDTO;
    }

    public List<Aluno> getAll(){
        return alunoRepository.findAll();
    }

    public Optional<AlunoDTO> getById(Long id){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if(optionalAluno.isPresent()){
            return Optional.of(this.toDTO(optionalAluno.get()));
        }else {
            return Optional.empty();
        }
//        return alunoRepository.findById(id).map(this::toDTO);
    }

    public AlunoDTO saveDto(AlunoDTO alunoDTO){
        Aluno aluno = this.fromDTO(alunoDTO);
        Aluno alunoBd = alunoRepository.save(aluno);
        return this.toDTO(alunoBd);
    }

    public Optional<AlunoDTO> updateAluno(Long id, AlunoDTO alunoDTO){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if(optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();
            aluno.setNome(alunoDTO.getNome());
            aluno.setCpf(alunoDTO.getCpf());
            aluno.setCurso(alunoDTO.getCurso());

            Aluno alunoUpdate = alunoRepository.save(aluno);

            return Optional.of(this.toDTO(alunoUpdate));
        }else {
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(alunoRepository.existsById(id)){
            alunoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
