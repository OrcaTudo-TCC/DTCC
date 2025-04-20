package tcc.orcatudo.services;

import java.util.List;
import java.util.Map;

import tcc.orcatudo.entitites.Fornecedor;

public interface FornecedorService {

    List<Fornecedor> getAllFornecedor();

    Fornecedor getFornecedorById(int id);

    Fornecedor getFornecedorByEmail(String email);

    boolean existsFornecedorByEmail(String email);

    boolean deleteFornecedorByID(int id);

    Fornecedor updateFornecedor(Map<String,String> campos , int id );

}
