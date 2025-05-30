package br.com.fiap.money_control_api.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.money_control_api.controller.TransactionController.TransactionFilter;
import br.com.fiap.money_control_api.model.Transaction;
import jakarta.persistence.criteria.Predicate;

public class TransactionSpecification {
    public static Specification<Transaction> withFilters(TransactionFilter filter){
        return (root,query,cb)->{
            List<Predicate> predicates = new ArrayList<>();
            if(filter.description() != null && !filter.description().isBlank()){
                predicates.add(cb.like(
                    cb.lower(root.get("description")),"%"+filter.description()));

            }
            if(filter.startDate() != null && filter.endDate()!=null){
                predicates.add(
                    cb.between(root.get("date"),filter.startDate(),filter.endDate())
                );

            }
            var arrayPredicates=predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };

    }
    
}
