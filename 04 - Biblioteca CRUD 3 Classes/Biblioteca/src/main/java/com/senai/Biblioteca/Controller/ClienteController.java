package com.senai.Biblioteca.Controller;

import com.senai.Biblioteca.DTO.ClienteDTO;
import com.senai.Biblioteca.Entity.Cliente;
import com.senai.Biblioteca.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAll(){
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Long id){
        Optional<ClienteDTO> clienteDTOOptional = clienteService.getById(id);
        if(clienteDTOOptional.isPresent()){
            return ResponseEntity.ok(clienteDTOOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO clienteDTOSave = clienteService.create(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        Optional<ClienteDTO> clienteDTOOptional = clienteService.updateCliente(id, clienteDTO);
        if(clienteDTOOptional.isPresent()){
            return ResponseEntity.ok(clienteDTOOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(clienteService.delete(id)){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
