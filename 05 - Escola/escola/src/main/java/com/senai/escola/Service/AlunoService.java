package com.senai.escola.Service;

import com.senai.escola.DTO.AlunoDTO;
import com.senai.escola.Entity.Aluno;
import com.senai.escola.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> getAllAlunosCPF(String cpf){
        return alunoRepository.findAllByCpf(cpf);
    }

    public Optional<AlunoDTO> getById(Long id){
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            AlunoDTO alunoDTO = new AlunoDTO();
            return Optional.of(alunoDTO.fromAluno(alunoOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    public AlunoDTO createAluno(AlunoDTO alunoDTO){
        Aluno aluno = alunoDTO.toAluno();
        aluno = alunoRepository.save(aluno);
        return alunoDTO.fromAluno(aluno);
    }

    public Optional<AlunoDTO> updateAluno(Long id, AlunoDTO alunoDTO){
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            aluno.setNome(alunoDTO.getNome());
            aluno.setCpf(alunoDTO.getCpf());
            aluno.setTurma(alunoDTO.getTurma());

            aluno = alunoRepository.save(aluno);

            return Optional.of(alunoDTO.fromAluno(aluno));
        }else{
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
