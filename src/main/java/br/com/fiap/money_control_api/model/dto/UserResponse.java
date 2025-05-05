package br.com.fiap.money_control_api.model.dto;

import br.com.fiap.money_control_api.components.UserRole;

public record UserResponse(Long id,String email,UserRole role) {}
