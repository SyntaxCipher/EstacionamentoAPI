package com.github.syntaxcipher.estacionamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.syntaxcipher.estacionamento.converts.VeiculoPatioConvert;
import com.github.syntaxcipher.estacionamento.dtos.inputs.VeiculoInput;
import com.github.syntaxcipher.estacionamento.dtos.outputs.VeiculoOutput;
import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;
import com.github.syntaxcipher.estacionamento.services.VeiculoPatioService;

@RestController
@RequestMapping("/patio")
public class PatioController {

	@Autowired
	private VeiculoPatioConvert veiculoConvert;

	@Autowired
	private VeiculoPatioService veiculoService;

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

	@GetMapping("/lista")
	public List<VeiculoOutput> listaTodosVeiculosPatio() {
		List<VeiculoPatioEntity> listaTodosVeiculos = veiculoService.listaTodosVeiculosPatio();
		return veiculoConvert.listEntityToListOutput(listaTodosVeiculos);
	}
	
	@GetMapping("/lista/entrada")
	public List<VeiculoOutput> listaEntradaVeiculosPatio() {
		List<VeiculoPatioEntity> veiculosEntradaPatio = veiculoService.listaEntradaVeiculosPatio();
		return veiculoConvert.listEntityToListOutput(veiculosEntradaPatio);
	}
	
	@GetMapping("/lista/saida")
	public List<VeiculoOutput> listaSaidaVeiculosPatio() {
		List<VeiculoPatioEntity> veiculosSaidaPatio = veiculoService.listaSaidaVeiculosPatio();
		return veiculoConvert.listEntityToListOutput(veiculosSaidaPatio);
	}
}
