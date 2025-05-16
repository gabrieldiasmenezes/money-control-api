package br.com.fiap.money_control_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.money_control_api.model.Transaction;

public interface TransactionRepository extends
        JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByTypeAndDateBetween(String type, LocalDate startDate, LocalDate endDate);

    // List<Transaction> findByDescriptionContainingIgnoringCase(String
    // description);

    // List<Transaction> findByDescriptionContainingIgnoringCaseAndDate(String
    // description, LocalDate date);

    // List<Transaction> findByDate(LocalDate date);

}