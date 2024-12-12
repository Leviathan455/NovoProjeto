package br.csi.Animo.controller;

import br.csi.Animo.infra.security.TokenServiceJWT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Path relacionado a operações de login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenServiceJWT tokenServiceJWT;

    public AutenticacaoController(AuthenticationManager manager, TokenServiceJWT tokenServiceJWT) {
        this.manager = manager;
        this.tokenServiceJWT = tokenServiceJWT;
    }

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosTokenJWT.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @Operation(summary = "Fazer login no sistema", description = "Faz o login e libera o token JWT")
    public ResponseEntity<?> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            UsernamePasswordAuthenticationToken loginData =
                    new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

            Authentication authentication = manager.authenticate(loginData);

            String token = tokenServiceJWT.gerarToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new DadosTokenJWT(token));

        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    private record DadosTokenJWT(String token) {}

    private record DadosAutenticacao(String email, String senha) {}
}
