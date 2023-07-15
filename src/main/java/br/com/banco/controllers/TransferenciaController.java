package br.com.banco.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.banco.models.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {
    @Autowired
    private TransferenciaRepository repo;

    @GetMapping
    public List<Transferencia> list (
        @Nullable @RequestParam(name = "id") String id, 
        @Nullable @RequestParam(name = "data_inicial") String data_inicial, 
        @Nullable @RequestParam(name = "data_final") String data_final,
        @Nullable @RequestParam(name = "nome_operador") String nome_operador
    ) {
        // Get all the transfers by operator and date if ALL the filters are filled
        if (nome_operador != null && data_inicial != null && data_final != null && id != null)
            return repo.findBynomeOperadorTransacaoAndDataTransferenciaBetween(nome_operador, parseDate(data_inicial), parseDate(data_final));
        // Get by operator name
        if (nome_operador != null) 
            return repo.findByNomeOperadorTransacao(nome_operador);
        // Get transfers by date range
        if (data_inicial != null && data_final != null) 
            return repo.findByDataTransferenciaBetween(parseDate(data_inicial), parseDate(data_final));
        // Get transfers by account ID
        if (id != null) 
            return repo.findByContaId(Long.parseLong(id));
        // Get all transfers
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transferencia add (@RequestBody Transferencia transferencia) {
        return repo.save(transferencia);
    }

    private Timestamp parseDate (String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate;
            parsedDate = dateFormat.parse(dateString);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            return null;
        }
    }
}
