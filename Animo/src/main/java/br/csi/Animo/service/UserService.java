package br.csi.Animo.service;

import br.csi.Animo.model.User;
import br.csi.Animo.repository.UserRepository;
import br.csi.Animo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Método para obter todos os usuários mapeados para UserDTO
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromUser)  // Converte de User para UserDTO
                .collect(Collectors.toList());
    }

    // Método para obter um usuário por ID
    public Optional<UserDTO> findById(Integer id) {
        return userRepository.findById(id)
                .map(UserDTO::fromUser);  // Converte para UserDTO
    }

    // Salvar novo usuário
    public User save(User user) {
        // Criptografando a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return userRepository.save(user);
    }

    // Atualizar usuário
    public Optional<User> update(Integer id, User user) {
        if (userRepository.existsById(id)) {
            if (user.getSenha() != null && !user.getSenha().isEmpty()) {
                // Se a senha foi alterada, criptografa a nova senha
                user.setSenha(passwordEncoder.encode(user.getSenha()));
            }
            user.setId(id);  // Garante que o ID seja o mesmo do parâmetro
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    // Deletar usuário
    public boolean delete(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
