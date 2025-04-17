package com.senai.Biblioteca.Controller;

import com.senai.Biblioteca.DTO.ClienteDTO;
import com.senai.Biblioteca.DTO.EmprestimoDTO;
import com.senai.Biblioteca.Entity.Emprestimo;
import com.senai.Biblioteca.Service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public List<Emprestimo> getAll(){
        return emprestimoService.getAllEmprestimos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> getById(@PathVariable Long id){
        Optional<EmprestimoDTO> emprestimoDTOOptional = emprestimoService.getById(id);
        if(emprestimoDTOOptional.isPresent()){
            return ResponseEntity.ok(emprestimoDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> create(@RequestBody EmprestimoDTO emprestimoDTO){
        EmprestimoDTO emprestimoDTOSave = emprestimoService.create(emprestimoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> update(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO){
        Optional<EmprestimoDTO> emprestimoDTOOptional = emprestimoService.update(id, emprestimoDTO);
        if(emprestimoDTOOptional.isPresent()){
            return ResponseEntity.ok(emprestimoDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(emprestimoService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
