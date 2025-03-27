package com.example.Curso.Controller;

import com.example.Curso.DTO.AlunoDTO;
import com.example.Curso.DTO.CursoDTORequest;
import com.example.Curso.DTO.CursoDTOResponse;
import com.example.Curso.DTO.ProfessorDTO;
import com.example.Curso.Entity.Curso;
import com.example.Curso.Service.CursoService;
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
    private CursoService cursoService;


    @GetMapping
    public ResponseEntity<List<Curso>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> getById(@PathVariable Long id){
        Optional<CursoDTOResponse> cursoDTO = cursoService.getById(id);
        if(cursoDTO.isPresent()){
            return ResponseEntity.ok(cursoDTO.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

//        return cursoDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoDTOResponse> created(@RequestBody CursoDTORequest cursoDtoRequest){
        CursoDTOResponse curso = cursoService.saveDto(cursoDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTOResponse> update(@PathVariable Long id, @RequestBody CursoDTORequest cursoDTORequest){
        Optional<CursoDTOResponse> cursoDTOResponseOp = cursoService.updateCurso(id, cursoDTORequest);
        if (cursoDTOResponseOp.isPresent()){
            return ResponseEntity.ok(cursoDTOResponseOp.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(cursoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
