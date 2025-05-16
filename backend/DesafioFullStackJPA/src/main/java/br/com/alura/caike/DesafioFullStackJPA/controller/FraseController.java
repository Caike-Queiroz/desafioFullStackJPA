package br.com.alura.caike.DesafioFullStackJPA.controller;

import br.com.alura.caike.DesafioFullStackJPA.DTOs.FraseDTO;
import br.com.alura.caike.DesafioFullStackJPA.service.FraseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class FraseController {

    private final FraseService fraseService;

    public FraseController(FraseService fraseService) {
        this.fraseService = fraseService;
    }

    @GetMapping("/frases")
    public FraseDTO obterFraseAleatoria() {
        return this.fraseService.obterFraseAleatoria();
    }
}