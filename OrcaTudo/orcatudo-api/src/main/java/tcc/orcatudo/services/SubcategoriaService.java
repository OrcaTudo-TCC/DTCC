package tcc.orcatudo.services;



import java.util.List;

import tcc.orcatudo.dtos.SubcategoriaDTO;
import tcc.orcatudo.entitites.Subcategoria;

public interface SubcategoriaService {

    List<Subcategoria> getByCategoria(String nomeCategoria);

    Subcategoria postSubcategoria(SubcategoriaDTO dto);

    Subcategoria updateNome(int idDaSubcategoria, String novoNome);

    Subcategoria updateCategoria(int idDaSubcategoria, int idDaCategoria);

    void deleteByid(int id);

}
