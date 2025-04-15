package tcc.orcatudo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tcc.orcatudo.dtos.FornecedorDTO;
import tcc.orcatudo.entitites.Fornecedor;

public interface FornecedorService {

    List<Fornecedor> getAllFornecedor();

    Fornecedor getFornecedorById(int id);

    Fornecedor getFornecedorByEmail(String email);

    boolean existsFornecedorByEmail(String email);

    Fornecedor updateFornecedor(FornecedorDTO fornecedor);

    boolean deleteFornecedorByID(int id);

}
