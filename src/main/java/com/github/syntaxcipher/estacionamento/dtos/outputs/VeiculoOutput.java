package com.github.syntaxcipher.estacionamento.dtos.outputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoOutput {

	private Long id;
	
	private Integer ticket;

	private String placa;

	private String modelo;

	private String cor;
	
	private Boolean noPatio;

}
