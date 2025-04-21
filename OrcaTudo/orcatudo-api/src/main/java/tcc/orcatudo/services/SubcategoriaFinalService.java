package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.dtos.SubcategoriaFinalDTO;
import tcc.orcatudo.entitites.SubcategoriaFinal;

public interface SubcategoriaFinalService {

    List<SubcategoriaFinal> getSubcategoriaFinalBySubcategoriaNome(String nomeSubcategoria);

    SubcategoriaFinal updateNomeSubcategoriaFinal(int idSubcategoriaFinal, String novoNome);

    SubcategoriaFinal updateSubcategoria(int idSubcategoriaFinal, int idSubcategoria);

    SubcategoriaFinal postSubcategoriaFinal(SubcategoriaFinalDTO dto);

    void deleteByid(int id);

}
