package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.dtos.FornecedorDTO;
import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.repository.FornecedorRepository;
import tcc.orcatudo.services.FornecedorService;

@Service
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
    public Fornecedor updateFornecedor(FornecedorDTO fornecedor) {
        if (!fornecedorRepository.existsById(fornecedor.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , "Fornecedor não encontrado para atualizar");
        }
        Fornecedor updatedFornecedor = Fornecedor.fromDTO(fornecedor);

        return fornecedorRepository.save(updatedFornecedor);
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
    public Fornecedor getFornecedorById(int id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    

}
