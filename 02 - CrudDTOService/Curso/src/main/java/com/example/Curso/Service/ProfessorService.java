package com.example.Curso.Service;

import com.example.Curso.DTO.ProfessorDTO;
import com.example.Curso.Entity.Professor;
import com.example.Curso.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;


    //converte professorDTO em professor
    public Professor fromDTO(ProfessorDTO professorDTO){
        Professor professor = new Professor();
        professor.setNome(professorDTO.getNome());
        professor.setCpf(professor.getCpf());

        return professor;
    }

    // converte Professor para ProfessorDTO
    public ProfessorDTO toDTO(Professor professor){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getIdProfessor());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());

        return professorDTO;
    }

    public List<Professor> getAll(){
        return professorRepository.findAll();
    }

    public Optional<ProfessorDTO> getById(Long id){
        // busca o professor no banco de dados com base no ID
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if(optionalProfessor.isPresent()){// verifica se encontrou algum professor
            // transforma a entidade professor para DTO
            // e também coloca dentro de um objeto Optional
            return Optional.of(this.toDTO(optionalProfessor.get()));
        }else {
            return Optional.empty(); // um objeto Optional vazio.
        }
//        return professorRepository.findById(id).map(this::toDTO);
    }

    public ProfessorDTO save(ProfessorDTO professorDTO){
        // converte de DTO para Entidade
        Professor professor = this.fromDTO(professorDTO);
        // salva no banco de dados a entidade
        Professor professorBd = professorRepository.save(professor);
        // da return transformando novamente para DTO
        return this.toDTO(professorBd);
    }

    public Optional<ProfessorDTO> updateProfessor(Long id, ProfessorDTO professorDTO){
        // busca o professor no banco de dados com base no ID enviado
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        //verifica se encontrou algum professor para ser atualizado
        if(optionalProfessor.isPresent()){
            // caso encontrar um professor, instancia uma entidade com o nome "professor", passando o professor que esta no banco de dados
            Professor professor = optionalProfessor.get();
            // atualizando os dados da entidade "professor" que veio do banco de dados
            professor.setNome(professorDTO.getNome());
            professor.setCpf(professorDTO.getCpf());

            // salva no banco dados o professor com o dados atualizados
            Professor professorUpdate = professorRepository.save(professor);

            // transforma a entidade professor que veio como retorno do banco de dados em um DTO para ser retornado
            return Optional.of(this.toDTO(professorUpdate));
        }else { // se nao encontrar retonar um Objeto Optinal vazio.
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
