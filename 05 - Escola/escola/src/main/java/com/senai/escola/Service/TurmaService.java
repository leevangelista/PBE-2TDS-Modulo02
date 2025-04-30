package com.senai.escola.Service;

import com.senai.escola.DTO.TurmaDTO;
import com.senai.escola.Entity.Aluno;
import com.senai.escola.Entity.Turma;
import com.senai.escola.Repository.AlunoRepository;
import com.senai.escola.Repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    // instanciado o Respositório do aluno
    // porque as operações de inserir e remover um aluno de uma turma precisa dos dois repositórios
    // Para inserir/remover um aluno em turma precisa buscar a turma que será inserido/removido o aluno e também o aluno que será alterado
    @Autowired
    private AlunoRepository alunoRepository;

    public List<Turma> getAllTurmas() {
        return turmaRepository.findAll();
    }

    public List<Turma> getAllTurmasByNome(String nome){
        return turmaRepository.getAllByNome(nome);
    }

    public Optional<TurmaDTO> getById(Long id){
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if(turmaOptional.isPresent()){
            TurmaDTO turmaDTO = new TurmaDTO();
            return Optional.of(turmaDTO.fromTurma(turmaOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    public TurmaDTO createTurma(TurmaDTO turmaDTO){
        Turma turma = turmaDTO.toTurma();
        turma = turmaRepository.save(turma);
        return turmaDTO.fromTurma(turma);
    }

    public Optional<TurmaDTO> updateTurma(Long id, TurmaDTO turmaDTO){
        Optional<Turma> turmaOptional = turmaRepository.findById(id);
        if(turmaOptional.isPresent()){
            Turma turma = turmaOptional.get();
            turma.setSigla(turmaDTO.getSigla());
            turma.setNumeroSala(turmaDTO.getNumeroSala());
            turma.setNome(turmaDTO.getNome());
            turma.setProfessor(turmaDTO.getProfessor());

            turma = turmaRepository.save(turma);

            return Optional.of(turmaDTO.fromTurma(turma));
        }else{
            return Optional.empty();
        }
    }

    // adiconar aluno a turma
    public boolean addAlunoTurma(Long id, Long idAluno){
        // busca a turma e verifica se ela existe
        Optional<Turma> optionalTurma = turmaRepository.findById(id);
        if(!optionalTurma.isPresent()){
            return false;
        }

        // busca o aluno e verifica se ele existe
        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);
        if(!optionalAluno.isPresent()){
            return false;
        }

        // instancia as entidades Turma e Aluno
        Turma turma = optionalTurma.get();
        Aluno aluno = optionalAluno.get();

        // atualiza a entidade aluno com a nova turma
        aluno.setTurma(turma);
        // salva no banco de dados
        alunoRepository.save(aluno);
        return true;
    }

    // remover aluno a turma
    public boolean removeAlunoTurma(Long id, Long idAluno){
        // busca o aluno e verifica se ele existe
        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);
        if(!optionalAluno.isPresent()){
            return false;
        }
        // instancia as entidades Turma e Aluno
        Aluno aluno = optionalAluno.get();

        // verifica se o aluno tem uma turma
        // verifica se a turma que esta no aluno é realmente a turma que deseja remover
        if (aluno.getTurma() != null && aluno.getTurma().getId().equals(id)) {
            aluno.setTurma(null); // remove o aluno da turma
            alunoRepository.save(aluno); // salva no banco de dados
            return true;
        }
        return false;
    }

    public boolean delete(Long id){
        if(turmaRepository.existsById(id)){
            turmaRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
