package br.com.fiap.money_control_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_control_api.model.Transaction;
import br.com.fiap.money_control_api.repository.TransactionRepository;
import br.com.fiap.money_control_api.service.AIAnalistyService;
import br.com.fiap.money_control_api.specification.TransactionSpecification;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("transactions")
@Slf4j
public class TransactionController {

    public record TransactionFilter(String description, LocalDate startDate, LocalDate endDate) {
    }

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AIAnalistyService aiService;

    @GetMapping
    public Page<Transaction> index(TransactionFilter filters,
            @PageableDefault(size = 10, sort = "date", direction = Direction.DESC) Pageable pageable) {
        var specification = TransactionSpecification.withFilters(filters);
        return repository.findAll(specification, pageable);
    }

    @GetMapping("/dashboard")
    public String getDashboardData(){
        return aiService.getExpenseAnalise();
    }

}