package com.kleverland.compras.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private int id;
    @Column(name = "nome")
//    @Length(min = 5, message = "*O nome deve ter no mínimo 5 caracteres.")
//    @NotEmpty(message = "*Favor inserir seu nome.")
    private String name;
    @Column(name = "sobrenome")
//    @NotEmpty(message = "*Favor inserir o sobrenome.")
    private String surname;
    @Column(name = "email")
//    @Email(message = "*Favor inserir um e-mail válido.")
//    @NotEmpty(message = "*Favor inserir um e-mail.")
    private String email;
    @Column(name = "senha")
//    @NotEmpty(message = "*Favor inserir uma senha.")
    private String password;
    @Column(name = "usuario")
//    @NotEmpty(message = "*Favor inserir um usuario.")
    private String user;
    @Column(name = "funcao")
//    @NotEmpty(message = "*Favor inserir uma funcao.")
    private String role;

}