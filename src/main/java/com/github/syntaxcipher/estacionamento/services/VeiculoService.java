package com.github.syntaxcipher.estacionamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;
import com.github.syntaxcipher.estacionamento.exceptions.NotFoundBussinessException;
import com.github.syntaxcipher.estacionamento.repositories.VeiculoPatioRepository;

import jakarta.transaction.Transactional;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoPatioRepository veiculoPatioRepository;

	@Transactional
	public VeiculoPatioEntity entrarVeiculoPatio(VeiculoPatioEntity veiculoConvertido) {
		veiculoConvertido.setTicket(veiculoPatioRepository.findAll().size() + 1);
		veiculoConvertido.setNoPatio(true);
		return veiculoPatioRepository.save(veiculoConvertido);
	}

	public VeiculoPatioEntity buscaVeiculoPeloTicket(Integer ticket) {
		VeiculoPatioEntity veiculoEncontrado = veiculoPatioRepository.findByTicket(ticket);
		if (veiculoEncontrado != null) {
			return veiculoEncontrado;
		} else {
			throw new NotFoundBussinessException("ticket não encontrado");
		}
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

}