package com.example.Curso.Controller;

import com.example.Curso.Entity.Curso;
import com.example.Curso.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.ok(cursoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(Long id){
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if(cursoOptional.isPresent()){
            Curso curso = cursoOptional.get();
            return ResponseEntity.ok(curso);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Curso> created(@RequestBody Curso curso){
        Curso cursoBd = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoBd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Curso updateCurso){
        Optional<Curso> optionalCurso = cursoRepository.findById(id);

        if(optionalCurso.isPresent()){
            Curso curso = optionalCurso.get();
            curso.setNome(updateCurso.getNome());
            curso.setNumeroSala(updateCurso.getNumeroSala());
            curso.setProfessor(updateCurso.getProfessor());

            return ResponseEntity.ok(cursoRepository.save(curso));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Curso> cursoOptional = cursoRepository.findById(id);

        if(cursoOptional.isPresent()){
            Curso curso = cursoOptional.get();
            cursoRepository.delete(curso);
            return ResponseEntity.ok("Curso deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não foi encontrado para ser deletado");
        }
    }
}
