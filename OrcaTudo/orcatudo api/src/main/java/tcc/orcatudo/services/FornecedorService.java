package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.entitites.Fornecedor;

public interface FornecedorService {

    Fornecedor getFornecedorById(int id);

    Fornecedor getFornecedorByEmail(String email);

    boolean existsFornecedorByEmail(String email);

    List<Fornecedor> getAllFornecedor();

    Fornecedor updateFornecedor(Fornecedor fornecedor);

    boolean deleteFornecedorByID(int id);

    long countFornecedor();

}
