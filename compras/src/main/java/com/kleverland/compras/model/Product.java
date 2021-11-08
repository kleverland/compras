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
@Table(name = "produto")
public class Product {
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_produto")
	private int id;
	@Column(name = "nome", nullable = true, length = 250)
	private String nome;
	@Column(name = "preco", nullable = true, length = 250)
	private float preco;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categoria")	
	private Category category;
}
