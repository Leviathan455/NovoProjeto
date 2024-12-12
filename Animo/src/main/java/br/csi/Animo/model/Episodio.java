package br.csi.Animo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "episodios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do episódio", example = "1")
    private Integer id;

    @NotNull
    @Schema(description = "Número do episódio", example = "1")
    private Integer numero;

    @NotNull
    @Schema(description = "Título do episódio", example = "Abertura")
    private String titulo;

    @NotNull
    @Schema(description = "Duração do episódio em minutos", example = "24")
    private Integer duracao; // Duração em minutos

    @Schema(description = "URL de streaming do episódio")
    private String urlStream;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "anime_id", nullable = false)
    @NotNull
    @Schema(description = "Anime associado ao episódio")
    private Anime anime;


    @Column(name = "created_at")
    @Schema(description = "Data de criação do episódio")
    private LocalDateTime createdAt;
}