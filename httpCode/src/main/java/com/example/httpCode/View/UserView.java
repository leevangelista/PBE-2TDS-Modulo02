package com.example.httpCode.View;

import com.example.httpCode.Controller.UserController;
import com.example.httpCode.Model.User;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserView {
    UserController userController = new UserController();

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return userController.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<User> getById(@PathVariable int id){
        return userController.getById(id);
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao inserir usuário")
    })
    public ResponseEntity<User> insert(@RequestBody User user){
        return userController.insertBanco(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable int id){
        return userController.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        return userController.delete(id);
    }
}