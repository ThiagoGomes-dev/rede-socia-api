package com.redesocial.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioCreateRequest {
    private String role;
    private String usuario;
    private String nome;
    private String email;
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    private String telefone;

    // getters e setters
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
