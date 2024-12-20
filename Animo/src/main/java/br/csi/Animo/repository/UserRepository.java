package br.csi.Animo.repository;

import br.csi.Animo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsuarioByUuid(UUID uuid);
    void deleteUsuarioByUuid(UUID uuid);

    public User findByEmail(String email);

}
