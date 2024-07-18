package com.github.syntaxcipher.estacionamento.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;
import com.github.syntaxcipher.estacionamento.repositories.VeiculoPatioRepository;

import jakarta.transaction.Transactional;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoPatioRepository veiculoPatioRepository;

	@Transactional
	public VeiculoPatioEntity entrarVeiculoPatio(VeiculoPatioEntity veiculoConvertido) {
		veiculoConvertido.setTicket(veiculoPatioRepository.findAll().size() + 1);
		return veiculoPatioRepository.save(veiculoConvertido);
	}

}