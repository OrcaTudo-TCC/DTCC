package tcc.orcatudo.services;



import java.util.List;

import tcc.orcatudo.entitites.Subcategoria;

public interface SubcategoriaService {

    List<Subcategoria> getByCategoria(String nomeCategoria);

    Subcategoria postSubcategoria(String nomeDaCategoria, String nome);

    Subcategoria updateNome(int idDaSubcategoria, String novoNome);

    Subcategoria updateCategoria(int idDaSubcategoria, int idDaCategoria);

    void deleteByid(int id);

}
