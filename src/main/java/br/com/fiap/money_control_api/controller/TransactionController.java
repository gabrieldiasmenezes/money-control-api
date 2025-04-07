package br.com.fiap.money_control_api.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_control_api.model.Transaction;
import br.com.fiap.money_control_api.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    record TransactionFilter (String description, LocalDate date, BigDecimal minAmount, BigDecimal maxAmount){}

    @Autowired
    private TransactionRepository repository;

    @GetMapping
    public List<Transaction> index(TransactionFilter filters){
        log.info("Buscando movimentações com descricao {} e date {} e valor {}", filters.description, filters.date, filters.maxAmount);
     
        var probe = Transaction.builder()
                        .description(filters.description)
                        .date(filters.date)
                        .amount(filters.maxAmount)
                        .build();
        
        var matcher = ExampleMatcher.matchingAll()
                            .withIgnoreCase()
                            .withIgnoreNullValues()
                            .withStringMatcher(StringMatcher.CONTAINING);
        
        var example = Example.of(probe,matcher);

        return repository.findAll(example);
    }
    
}