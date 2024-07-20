package com.github.syntaxcipher.estacionamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.syntaxcipher.estacionamento.converts.VeiculoConvert;
import com.github.syntaxcipher.estacionamento.dtos.inputs.VeiculoInput;
import com.github.syntaxcipher.estacionamento.dtos.outputs.VeiculoOutput;
import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;
import com.github.syntaxcipher.estacionamento.services.VeiculoService;

@RestController
@RequestMapping("/patio")
public class PatioController {

	@Autowired
	private VeiculoConvert veiculoConvert;

	@Autowired
	private VeiculoService veiculoService;

	@PostMapping("/entrada")
	public VeiculoOutput entrarVeiculoPatio(@RequestBody VeiculoInput veiculoInput) {
		VeiculoPatioEntity veiculoConvertido = veiculoConvert.inputToEntity(veiculoInput);
		VeiculoPatioEntity veiculoNoPatio = veiculoService.entrarVeiculoPatio(veiculoConvertido);
		return veiculoConvert.entityToOutput(veiculoNoPatio);
	}

	@PutMapping("/saida/{ticket}")
	public VeiculoOutput saidaVeiculoPatio(@PathVariable Integer ticket) {
		VeiculoPatioEntity veiculoEncontrado = veiculoService.buscaVeiculoPeloTicket(ticket);
		return veiculoConvert.entityToOutput(veiculoService.saidaVeiculoPatio(veiculoEncontrado));
	}

}
