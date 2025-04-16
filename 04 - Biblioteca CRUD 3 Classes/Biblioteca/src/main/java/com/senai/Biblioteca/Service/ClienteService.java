package com.senai.Biblioteca.Service;

import com.senai.Biblioteca.DTO.ClienteDTO;
import com.senai.Biblioteca.Entity.Cliente;
import com.senai.Biblioteca.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteDTO> getById(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            ClienteDTO produtoDTO = new ClienteDTO();
            return Optional.of(produtoDTO.fromCliente(clienteOptional.get()));
        }else {
            return Optional.empty();
        }
    }

    public ClienteDTO create(ClienteDTO clienteDTO){
        Cliente cliente = clienteDTO.toCliente();
        cliente = clienteRepository.save(cliente);
        return clienteDTO.fromCliente(cliente);
    }

    public Optional<ClienteDTO> updateCliente(Long id, ClienteDTO clienteDTO){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setSobrenome(clienteDTO.getSobrenome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente = clienteRepository.save(cliente);
            return Optional.of(clienteDTO.fromCliente(cliente));
        }else{
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
