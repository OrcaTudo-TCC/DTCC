package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.entitites.Categoria;

public interface CategoriaService {

    List<Categoria> getCategoria();

    Categoria updateCategoriaNome(int id, String novoNome);

    void deleteByid(int id);

    Categoria postCategoria(String nome);

}
