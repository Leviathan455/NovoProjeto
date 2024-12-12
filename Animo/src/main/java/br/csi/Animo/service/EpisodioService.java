package br.csi.Animo.service;

import br.csi.Animo.model.Episodio;
import br.csi.Animo.repository.EpisodioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodioService {

    @Autowired
    private EpisodioRepository episodioRepository;

    public List<Episodio> findAll() {
        return episodioRepository.findAll();
    }

    public Optional<Episodio> findById(Integer id) {
        return episodioRepository.findById(id);
    }

    public Episodio save(Episodio episodio) {
        return episodioRepository.save(episodio);
    }

    public Optional<Episodio> update(Integer id, Episodio episodio) {
        if (episodioRepository.existsById(id)) {
            episodio.setId(id);
            return Optional.of(episodioRepository.save(episodio));
        }
        return Optional.empty();
    }

    public boolean delete(Integer id) {
        if (episodioRepository.existsById(id)) {
            episodioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}