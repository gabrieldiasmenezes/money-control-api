package br.com.fiap.money_control_api.service;

import java.time.LocalDate;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.money_control_api.repository.TransactionRepository;

@Service
public class AIAnalistyService {

    private ChatClient chatClient;
    private TransactionRepository repository;

    public AIAnalistyService(ChatClient.Builder builder, Resource systemMessage, TransactionRepository repository) {
        this.repository = repository;
        this.chatClient = builder
                .defaultSystem(systemMessage)
                .build();
    }

    public String getExpenseAnalise(){
        var expenses = repository.findByTypeAndDateBetween("out", LocalDate.now().withDayOfMonth(1), LocalDate.now());

        var objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(expenses);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return chatClient.prompt()
            .user("forneça uma análise sobre as minhas despesas do mês atual. aqui estão as despesas no formato json: " + json)
            //system(sp -> sp.param("language", lang))
            .call()
            .content();
    }

    
}