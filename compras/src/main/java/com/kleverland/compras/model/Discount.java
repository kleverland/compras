package com.kleverland.compras.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "desconto")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_desconto")
    private int id_desconto;
    @Column(name = "cupom")
    @Length(min = 5, message = "*O cumpom possui pelo menos 5 caracteres.")
    @NotEmpty(message = "*Please provide a user name")
    private String cupom;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categoria")	
    private Category category;
    @Column(name = "valor_desconto")
    @NotEmpty(message = "*Favor inserir valor do desconto.")
    private int valorDesconto;
    @Column(name = "valor_minimo")
    @NotEmpty(message = "*Favor inserir valor mínimo para o desconto.")
    private int valorMinimo;
    @Column(name = "ativo")
    private Boolean ativo;
    @Column(name = "tipo_fixo")
    private Boolean tipoFixo;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario")
    private User criador;
}