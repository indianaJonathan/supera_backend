package br.com.banco.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.models.Conta;
import br.com.banco.repositories.ContaRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private ContaRepository repo;

    @GetMapping
    public List<Conta> list () {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta add (@RequestBody Conta conta) {
        return repo.save(conta);
    }
}
