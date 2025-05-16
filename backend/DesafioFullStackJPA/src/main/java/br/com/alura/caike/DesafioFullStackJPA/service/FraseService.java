package br.com.alura.caike.DesafioFullStackJPA.service;

import br.com.alura.caike.DesafioFullStackJPA.DTOs.FraseDTO;
import br.com.alura.caike.DesafioFullStackJPA.model.Frase;
import br.com.alura.caike.DesafioFullStackJPA.repository.FraseRepository;
import org.springframework.stereotype.Service;

@Service
public class FraseService {

    private final FraseRepository fraseRepository;

    public FraseService(FraseRepository fraseRepository) {
        this.fraseRepository = fraseRepository;
    }

    public FraseDTO obterFraseAleatoria() {
        Frase frase = this.fraseRepository.buscaFraseAleatoria();
        return this.convertToDTO(frase);
    }

    private FraseDTO convertToDTO(Frase frase) {
        return new FraseDTO(
                frase.getTitulo(),
                frase.getFrase(),
                frase.getPersonagem(),
                frase.getPoster()
        );
    }
}