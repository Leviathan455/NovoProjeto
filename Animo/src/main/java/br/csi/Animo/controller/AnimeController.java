package br.csi.Animo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import br.csi.Animo.model.Anime;
import br.csi.Animo.service.AnimeService;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    //1. Listar todos os animes sem paginação
    @Operation(summary = "Lista todos os animes", description = "Retorna uma lista com todos os animes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<Anime>> getAllAnimes() {
        try {
            List<Anime> animes = animeService.findAll(); // Chama o serviço para buscar todos os animes
            return ResponseEntity.ok(animes); // Retorna a lista com status 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de falha
        }
    }





    // 2. Buscar um anime por ID
    @Operation(summary = "Busca um anime por ID", description = "Retorna um anime específico baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable @Valid Integer id) {  // Validar id
        return animeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }





    // 3. Criar um novo anime
    @Operation(summary = "Cria um novo anime", description = "Adiciona um novo anime ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anime criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados fornecidos")
    })
    @PostMapping("/post")
    public ResponseEntity<Anime> createAnime(@Valid @RequestBody Anime anime) {  // Validação do body
        Anime savedAnime = animeService.save(anime);
        return ResponseEntity.status(201).body(savedAnime);
    }




    // 4. Atualizar um anime existente
    @Operation(summary = "Atualiza um anime existente", description = "Atualiza as informações de um anime baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anime atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable @Valid Integer id, @RequestBody @Valid Anime anime) {
        return animeService.update(id, anime)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. Deletar um anime
    @Operation(summary = "Deleta um anime", description = "Remove um anime do sistema baseado no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Anime deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anime não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable @Valid Integer id) {
        if (animeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
