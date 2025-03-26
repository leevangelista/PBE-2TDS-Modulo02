package com.example.Curso.Controller;

import com.example.Curso.Entity.Aluno;
import com.example.Curso.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        return ResponseEntity.ok(alunoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getById(Long id){
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);
        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            return ResponseEntity.ok(aluno);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> created(@RequestBody Aluno aluno){
        Aluno alunoBd = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoBd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Aluno updateAluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);

        if(optionalAluno.isPresent()){
            Aluno aluno = optionalAluno.get();
            aluno.setNome(updateAluno.getNome());
            aluno.setCpf(updateAluno.getCpf());
            aluno.setCurso(updateAluno.getCurso());

            return ResponseEntity.ok(alunoRepository.save(aluno));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Aluno> alunoOptional = alunoRepository.findById(id);

        if(alunoOptional.isPresent()){
            Aluno aluno = alunoOptional.get();
            alunoRepository.delete(aluno);
            return ResponseEntity.ok("Aluno deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não foi encontrado para ser deletado");
        }
    }}
