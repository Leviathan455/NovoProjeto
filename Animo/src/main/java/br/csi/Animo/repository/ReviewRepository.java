package br.csi.Animo.repository;

import br.csi.Animo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}