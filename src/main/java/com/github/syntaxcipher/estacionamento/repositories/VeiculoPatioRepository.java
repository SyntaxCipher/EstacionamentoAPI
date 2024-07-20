package com.github.syntaxcipher.estacionamento.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;

public interface VeiculoPatioRepository extends JpaRepository<VeiculoPatioEntity, Long> {

	VeiculoPatioEntity findByTicket(Integer ticket);

	VeiculoPatioEntity findByPlaca(String placa);
	
	List<VeiculoPatioEntity> findByNoPatio(boolean b);



}
