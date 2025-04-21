package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.repository.CategoriaRepository;
import tcc.orcatudo.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria updateCategoriaNome(int id, String novoNome) {
        Categoria toUpdate = categoriaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma Categoria encontrada com id: "+ id));
        toUpdate.setNome(novoNome);
        return categoriaRepository.save(toUpdate);
    }
    

    @Override
    public Categoria postCategoria(String nome) {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteByid(int id) {
        categoriaRepository.delete(categoriaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma categoria encontrada com id: "+ id)));
    }
    

    

}
