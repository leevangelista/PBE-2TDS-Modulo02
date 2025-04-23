package com.senai.Usuario.Controller;

import com.senai.Usuario.DTO.UsuarioDTO;
import com.senai.Usuario.Entity.Usuario;
import com.senai.Usuario.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    // 1 - Buscar todos
    @GetMapping
    public List<Usuario> getAll(@RequestParam(required = false) String nome, // 7 - Buscar com base no nome
                                @RequestParam(required = false)String cpf) // 8 - Buscar com base no cpf
    {
        if(nome != null && !nome.isEmpty()){
            return usuarioService.getAllByNome(nome);
        } else if (cpf != null && !cpf.isEmpty()) {
            return usuarioService.getAllByCpf(cpf);
        }

        return usuarioService.getAll();
    }

    // 2 - buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.getById(id);
        if(usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3 - cadastrar usuário
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioDTOSave = usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOSave);
    }

    // 4 - Atualizar usuário menos username e senha
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuario(id, usuarioDTO);
        if(usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // 6 - Atualizar senha do usuário
    @PutMapping("/{id}/senha")
    public ResponseEntity<UsuarioDTO> updateSenha(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuarioSenha(id, usuarioDTO);
        if(usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // 5 - deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(usuarioService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
