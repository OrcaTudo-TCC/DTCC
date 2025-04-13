package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.repository.FornecedorRepository;
import tcc.orcatudo.services.FornecedorService;

public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Override
    public Fornecedor getFornecedorByEmail(String email) {
        return fornecedorRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
    }

    @Override
    public boolean existsFornecedorByEmail(String email) {
        return fornecedorRepository.existsByEmail(email);
    }

    @Override
    public List<Fornecedor> getAllFornecedor() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor updateFornecedor(Fornecedor fornecedor) {
        if (!fornecedorRepository.existsById(fornecedor.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Fornecedor não encontrado para atualizar");
        }
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public boolean deleteFornecedorByID(int id) {
        if (fornecedorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Fornecedor não encontrado para deletar");
        }
        fornecedorRepository.deleteById(id);
        return true;
    }

    @Override
    public long countFornecedor() {
        return fornecedorRepository.count();
    }

    @Override
    public Fornecedor getFornecedorById(int id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    

}
