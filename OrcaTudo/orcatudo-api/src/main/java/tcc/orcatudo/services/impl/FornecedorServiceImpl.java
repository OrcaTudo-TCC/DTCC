package tcc.orcatudo.services.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Fornecedor updateFornecedor(HashMap<String,String> campos , int id) {
        Fornecedor toUpdate = fornecedorRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum fornecedor encontrado com id: "+ id));

        campos.forEach((campo , valor) -> {
            switch (campo) {
                case "nome":
                    toUpdate.setNome(valor);
                    break;
                case "razao_social":
                    toUpdate.setRazao_social(valor);
                    break;
                case "email":
                    toUpdate.setEmail(valor);
                    break;
                case "telefone":
                    toUpdate.setTelefone(valor);
                    break;
                case "endereco":
                    toUpdate.setEndereco(valor);
                    break;
                case "documento":
                    toUpdate.setDocumento(valor);
                    break;
                case "area_de_atuacao":
                    toUpdate.setArea_de_atuacao(valor);
                    break;
                case "descricao":
                    toUpdate.setDescricao(valor);
                    break;
                case "avaliacao":
                    toUpdate.setAvaliacao(Integer.parseInt(valor));
                    break;
                case "senha":
                    toUpdate.setSenha(valor);
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum campo de fornecedor com nome: "+ campo);
            }
        });

        return fornecedorRepository.save(toUpdate);
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
