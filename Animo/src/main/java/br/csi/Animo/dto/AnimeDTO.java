package br.csi.Animo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimeDTO {
    @NotBlank(message = "O nome do anime é obrigatório.")
    private String nome;

    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
    private String descricao;

    @NotNull(message = "O ano de lançamento é obrigatório.")
    private Integer anoLancamento;

    public @NotBlank(message = "O nome do anime é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome do anime é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Size(max = 255, message = "A descrição não pode exceder 255 caracteres.") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O ano de lançamento é obrigatório.") Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(@NotNull(message = "O ano de lançamento é obrigatório.") Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
