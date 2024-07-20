package com.github.syntaxcipher.estacionamento.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;
import com.github.syntaxcipher.estacionamento.exceptions.NotFoundBussinessException;
import com.github.syntaxcipher.estacionamento.repositories.VeiculoPatioRepository;

import jakarta.transaction.Transactional;

@Service
public class VeiculoPatioService {

	@Autowired
	private VeiculoPatioRepository veiculoPatioRepository;

	@Transactional
	public VeiculoPatioEntity entrarVeiculoPatio(VeiculoPatioEntity veiculoConvertido) {
		veiculoConvertido.setTicket(veiculoPatioRepository.findAll().size() + 1);
		veiculoConvertido.setNoPatio(true);
		return veiculoPatioRepository.save(veiculoConvertido);
	}
	
	@Transactional
	public VeiculoPatioEntity saidaVeiculoPatio(VeiculoPatioEntity veiculoEncontrado) {
		if (veiculoEncontrado.getNoPatio() == true) {
			veiculoEncontrado.setNoPatio(false);
			return veiculoPatioRepository.save(veiculoEncontrado);
		} else {
			throw new NotFoundBussinessException("Veículo não se encontra no patio");
		}
	}

	
	public VeiculoPatioEntity buscaVeiculoPeloTicket(Integer ticket) {
		VeiculoPatioEntity veiculoEncontrado = veiculoPatioRepository.findByTicket(ticket);
		if (veiculoEncontrado != null) {
			return veiculoEncontrado;
		} else {
			throw new NotFoundBussinessException("ticket não encontrado");
		}
	}

	public List<VeiculoPatioEntity> listaTodosVeiculosPatio() {
		return veiculoPatioRepository.findAll();
	}

	public List<VeiculoPatioEntity> listaEntradaVeiculosPatio() {
		return veiculoPatioRepository.findByNoPatio(true);
	}

	public List<VeiculoPatioEntity> listaSaidaVeiculosPatio() {
		return veiculoPatioRepository.findByNoPatio(false);
	}

	public VeiculoPatioEntity buscaVeiculoPelaPlaca(String placa) {
		VeiculoPatioEntity veiculoEncontrado = veiculoPatioRepository.findByPlaca(placa);
		if (veiculoEncontrado != null) {
			return veiculoEncontrado;
		} else {
			throw new NotFoundBussinessException("Placa não encontrada");
		}
	}
}