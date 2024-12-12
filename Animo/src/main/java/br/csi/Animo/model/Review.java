package br.csi.Animo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID da review", example = "1")
    private Integer id;

    @NotNull
    @Schema(description = "Nota da review (de 1 a 5)", example = "4")
    private Integer nota; // Avaliação de 1 a 5

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(description = "Usuário que fez a review")
    private User user;

    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    @Schema(description = "Anime que foi avaliado")
    private Anime anime;

    @Column(name = "created_at")
    @Schema(description = "Nome do revisor")
    private String name;

    // Construtor específico para o nome
    public Review(String name) {
        this.name = name;
    }
}