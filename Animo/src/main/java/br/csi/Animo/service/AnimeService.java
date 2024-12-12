package br.csi.Animo.service;

import br.csi.Animo.model.Anime;
import br.csi.Animo.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository animeRepository;

    // Método para obter todos os animes com seus episódios
    public List<Anime> findAll() {
        return animeRepository.findAllWithEpisodes();  // Chama o método personalizado no repositório
    }

    // Método para obter um anime por ID
    public Optional<Anime> findById(Integer id) {
        return animeRepository.findById(id);
    }

    // Método para salvar um novo anime
    public Anime save(Anime anime) {
        anime.setId(null); // Garante que o id seja null, permitindo que o banco de dados gere um novo id
        return animeRepository.save(anime);
    }


    // Método para atualizar um anime
    public Optional<Anime> update(Integer id, Anime anime) {
        if (animeRepository.existsById(id)) {
            anime.setId(id);  // Garante que o ID do anime seja o mesmo do parâmetro
            return Optional.of(animeRepository.save(anime));
        }
        return Optional.empty();  // Caso o anime não exista
    }

    // Método para deletar um anime
    public boolean delete(Integer id) {
        if (animeRepository.existsById(id)) {
            animeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
