package com.senai.escola.Controller;

import com.senai.escola.DTO.TurmaDTO;
import com.senai.escola.Entity.Turma;
import com.senai.escola.Service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public List<Turma> getAll(@RequestParam(required = false) String nome){
        if(nome != null && !nome.isEmpty()){
            return turmaService.getAllTurmasByNome(nome); // buscar com base no nome
        }
        return turmaService.getAllTurmas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDTO> getById(@PathVariable Long id){
        Optional<TurmaDTO> turmaDTOOptional = turmaService.getById(id);
        if(turmaDTOOptional.isPresent()){
            return ResponseEntity.ok(turmaDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDTO turmaDTO){
        TurmaDTO turmaDTOSave = turmaService.createTurma(turmaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> update(@PathVariable Long id, @RequestBody TurmaDTO turmaDTO){
        Optional<TurmaDTO> turmaDTOOptional = turmaService.updateTurma(id, turmaDTO);
        if(turmaDTOOptional.isPresent()){
            return ResponseEntity.ok(turmaDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // adicionar o aluno a turma
    @PutMapping("/{id}/aluno-add/{idAluno}")
    public ResponseEntity<String> addAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){
         if(turmaService.addAlunoTurma(id, idAluno)){ // verifica se deu certo adicionar o aluno na turma
             return ResponseEntity.ok("Aluno adicionado com sucesso");
         }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno ou Professor não encontrado");
        }
    }

    // remove aluno da turma
    @PutMapping("/{id}/aluno-remove/{idAluno}")
    public ResponseEntity<String> removeAlunoTurma(@PathVariable Long id, @PathVariable Long idAluno){
        if(turmaService.removeAlunoTurma(id, idAluno)){
            return ResponseEntity.ok("Aluno removido da turma com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao remover aluno da turma");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(turmaService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
