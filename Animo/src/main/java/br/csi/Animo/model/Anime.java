package br.csi.Animo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "animes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID do anime", example = "1")
    private Integer id;

    @NotNull
    @Size(max = 100, message = "O título do anime não pode ter mais de 100 caracteres")
    @Schema(description = "Título do anime", example = "Naruto")
    private String titulo;

    @NotNull
    @Size(max = 50, message = "O gênero do anime não pode ter mais de 50 caracteres")
    @Schema(description = "Gênero do anime", example = "Ação")
    private String genero;

    @NotNull
    @Size(max = 500, message = "A descrição do anime não pode ter mais de 500 caracteres")
    @Schema(description = "Descrição do anime", example = "Um jovem ninja busca se tornar o Hokage.")
    private String descricao;

    @Column(name = "created_at")
    @Schema(description = "Data de criação do anime")
    private LocalDateTime createdAt;

    @Schema(description = "Lista de episódios do anime")
    @OneToMany(mappedBy = "anime", fetch = FetchType.EAGER) // Carrega os episódios antecipadamente
    private List<Episodio> episodes;

}