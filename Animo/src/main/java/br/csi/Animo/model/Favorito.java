package br.csi.Animo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "favoritos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do favorito", example = "1")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuário que adicionou o favorito")
    private User user;

    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    @Schema(description = "Anime que foi adicionado aos favoritos")
    private Anime anime;

    @Column(name = "created_at")
    @Schema(description = "Data de criação do favorito")
    private LocalDateTime createdAt;
}