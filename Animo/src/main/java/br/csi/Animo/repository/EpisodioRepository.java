package br.csi.Animo.repository;

import br.csi.Animo.model.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio, Integer> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}