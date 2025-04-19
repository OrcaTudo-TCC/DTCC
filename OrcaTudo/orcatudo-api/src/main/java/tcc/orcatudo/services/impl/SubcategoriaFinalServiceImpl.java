package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.entitites.SubcategoriaFinal;
import tcc.orcatudo.repository.SubcategoriaFinalRepository;
import tcc.orcatudo.repository.SubcategoriaRepository;
import tcc.orcatudo.services.SubcategoriaFinalService;

@Service
public class SubcategoriaFinalServiceImpl implements SubcategoriaFinalService{

    @Autowired
    SubcategoriaFinalRepository subFinalRepository;

    @Autowired
    SubcategoriaRepository subRepository;
    
    @Override
    public List<SubcategoriaFinal> getSubcategoriaFinalBySubcategoriaNome(String nomeSubcategoria) {
        return subFinalRepository.findAllBySubcategoriaNome(nomeSubcategoria);
    }

    @Override
    public SubcategoriaFinal updateNomeSubcategoriaFinal(int idSubcategoriaFinal, String novoNome) {
        SubcategoriaFinal subFinalToUpdate = subFinalRepository.findById(idSubcategoriaFinal)
        .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma subcategoria final encontrada com id: "+ idSubcategoriaFinal)));
        subFinalToUpdate.setNome(novoNome);
        return subFinalRepository.save(subFinalToUpdate);
    }

    @Override
    public SubcategoriaFinal updateSubcategoria(int idSubcategoriaFinal, int idSubcategoria) {
        Subcategoria subcategoria = subRepository.findById(idSubcategoria)
        .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma subcategoria encontrada com id: "+ idSubcategoria)));
        SubcategoriaFinal toUpdate = subFinalRepository.findById(idSubcategoriaFinal)
        .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma subcategoria final encontrada com id: "+ idSubcategoriaFinal)));
        toUpdate.setSubcategoria(subcategoria);
        return subFinalRepository.save(toUpdate);

    }

    @Override
    public void deleteByid(int id) {
        subFinalRepository.delete(subFinalRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhuma subcategoria final encontrada com id: "+ id)));
    }
    

}
