package com.example.Curso.Controller;

import com.example.Curso.DTO.AlunoDTO;
import com.example.Curso.Entity.Aluno;
import com.example.Curso.Service.AlunoService;
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
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable Long id){
        Optional<AlunoDTO> alunoDTO = alunoService.getById(id);
        if(alunoDTO.isPresent()){
            return ResponseEntity.ok(alunoDTO.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        return alunoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> created(@RequestBody AlunoDTO alunoDto){
        AlunoDTO aluno = alunoService.saveDto(alunoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id, @RequestBody AlunoDTO alunoDTO){
        Optional<AlunoDTO> alunoDTOOptional = alunoService.updateAluno(id, alunoDTO);
        if (alunoDTOOptional.isPresent()){
            return ResponseEntity.ok(alunoDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(alunoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
