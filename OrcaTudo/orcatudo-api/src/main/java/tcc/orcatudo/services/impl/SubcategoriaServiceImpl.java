package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.repository.CategoriaRepository;
import tcc.orcatudo.repository.SubcategoriaRepository;
import tcc.orcatudo.services.SubcategoriaService;

@Service
public class SubcategoriaServiceImpl implements SubcategoriaService{

    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Override
    public List<Subcategoria> getByCategoria(String nomeCategoria) {
        return subcategoriaRepository.findAllByCategoriaNome(nomeCategoria);
    }
    @Override
    public Subcategoria postSubcategoria(String nomeDaCategoria, String nome) {
        Categoria categoria = categoriaRepository.findByNome(nomeDaCategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma Categoria encontrada com nome: "+ nomeDaCategoria));
        Subcategoria toSave = new Subcategoria();
        toSave.setCategoria(categoria);
        toSave.setNome(nome);
        return subcategoriaRepository.save(toSave);
    }
    @Override
    public Subcategoria updateNome(int idDaSubcategoria, String novoNome) {
        Subcategoria toUpdate = subcategoriaRepository.findById(idDaSubcategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nehuma subcategoria com id: "+ idDaSubcategoria));
        toUpdate.setNome(novoNome);
        return subcategoriaRepository.save(toUpdate);
    }
    @Override
    public Subcategoria updateCategoria(int idDaSubcategoria, int idDaCategoria) {
        Categoria categoria = categoriaRepository.findById(idDaCategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma Subcategoria com id: "+ idDaCategoria));
        Subcategoria toUpdate = subcategoriaRepository.findById(idDaSubcategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma Subcategoria com id: "+ idDaSubcategoria));
        toUpdate.setCategoria(categoria);
        return subcategoriaRepository.save(toUpdate);
    }
    @Override
    public void deleteByid(int id) {
        subcategoriaRepository.delete(subcategoriaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma subcategoria encontrada com id: " + id)));
    }
    
    

}
