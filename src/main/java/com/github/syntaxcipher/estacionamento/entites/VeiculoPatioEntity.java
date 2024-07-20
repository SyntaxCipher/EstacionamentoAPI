package com.github.syntaxcipher.estacionamento.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_veiculos_patio")
public class VeiculoPatioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ticket")
	private Integer ticket;
	
	@Column(name = "placa")
	private String placa;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "cor")
	private String cor;
	
	@Column(name = "noPatio")
	private Boolean noPatio;

}
