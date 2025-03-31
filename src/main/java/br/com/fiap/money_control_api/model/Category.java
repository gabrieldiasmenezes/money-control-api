package br.com.fiap.money_control_api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "o nome é obrigatório")
    @Size(min=3,max=255,message = "deve ter pelo menos 3 letras")
    private String name;
    @NotBlank(message = "o icone é obrigatório")
    private String icon;

    
}
