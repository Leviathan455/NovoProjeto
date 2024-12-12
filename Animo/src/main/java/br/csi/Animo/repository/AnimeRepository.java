package br.csi.Animo.repository;

import br.csi.Animo.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Integer> {

    // Método personalizado para carregar animes com episódios
    @Query("SELECT a FROM Anime a LEFT JOIN FETCH a.episodes") // LEFT JOIN para carregar os episódios
    List<Anime> findAllWithEpisodes();  // Retorna todos os animes com seus episódios carregados
}
