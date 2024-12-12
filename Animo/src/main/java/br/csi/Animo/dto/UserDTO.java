package br.csi.Animo.dto;

import br.csi.Animo.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "O nome do usuário é obrigatório.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static UserDTO fromUser(User user) {
        UserDTO dto = new UserDTO();
        dto.setNome(user.getNome());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toUser() {
        User user = new User();
        user.setNome(this.getNome());
        user.setEmail(this.getEmail());
        user.setSenha(this.getSenha()); // Certifique-se de criptografar a senha no serviço
        return user;
    }


}
