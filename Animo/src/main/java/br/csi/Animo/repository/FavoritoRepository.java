package br.csi.Animo.repository;

import br.csi.Animo.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}