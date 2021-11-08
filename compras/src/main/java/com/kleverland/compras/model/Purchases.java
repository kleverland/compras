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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "compra")
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_compra")
    private int id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_produto")
    private Product product;    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario")
    private User cliente;
    @Column(name = "qtd_produto")
    private int qtdProduto;
}
