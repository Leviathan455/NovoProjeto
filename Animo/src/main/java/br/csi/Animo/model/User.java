package br.csi.Animo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do usuário", example = "1")
    private Integer id;

    @NotBlank
    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;

    @NotBlank
    @Email
    @Schema(description = "Email do usuário", example = "joao.silva@example.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuário")
    private String senha;

    @Column(name = "created_at")
    @Schema(description = "Data de criação do usuário")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(description = "Lista de favoritos do usuário")
    private List<Favorito> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Schema(description = "Lista de reviews do usuário")
    private List<Review> reviews;

    // Novo atributo de permissão
    @Column(name = "permissao")
    @Schema(description = "Permissão do usuário", example = "ROLE_ADMIN")
    private String permissao;  // Exemplo: "ROLE_ADMIN", "ROLE_USER"


    // Getters e setters (já fornecidos pelo Lombok)
}
