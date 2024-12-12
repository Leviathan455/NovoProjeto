package br.csi.Animo.service;

import br.csi.Animo.model.Favorito;
import br.csi.Animo.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    public List<Favorito> findAll() {
        return favoritoRepository.findAll();
    }

    public Optional<Favorito> findById(Integer id) {
        return favoritoRepository.findById(id);
    }

    public Favorito save(Favorito favorito) {
        return favoritoRepository.save(favorito);
    }

    public Optional<Favorito> update(Integer id, Favorito favorito) {
        if (favoritoRepository.existsById(id)) {
            favorito.setId(id);
            return Optional.of(favoritoRepository.save(favorito));
        }
        return Optional.empty();
    }

    public boolean delete(Integer id) {
        if (favoritoRepository.existsById(id)) {
            favoritoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}