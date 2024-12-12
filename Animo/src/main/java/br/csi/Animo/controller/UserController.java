package br.csi.Animo.controller;

import br.csi.Animo.dto.UserDTO;
import br.csi.Animo.model.User;
import br.csi.Animo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll()
                .stream()
                .map((UserDTO user) -> UserDTO.fromUser(user.toUser()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Busca um usuário por ID", description = "Retorna o usuário correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return userService.findById(id)
                .map((UserDTO user) -> UserDTO.fromUser(user.toUser()))   // Converte User para UserDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria um novo usuário", description = "Adiciona um novo usuário ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userDTO.toUser(); // Converte UserDTO para User
        User savedUser = userService.save(user);
        return ResponseEntity.status(201).body(UserDTO.fromUser(savedUser));
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza as informações do usuário com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDTO userDTO) {
        User user = userDTO.toUser(); // Converte UserDTO para User
        return userService.update(id, user)
                .map(UserDTO::fromUser)  // Converte User para UserDTO
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta um usuário", description = "Remove o usuário correspondente ao ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
