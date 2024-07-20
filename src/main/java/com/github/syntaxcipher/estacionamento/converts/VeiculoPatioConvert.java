package com.github.syntaxcipher.estacionamento.converts;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.github.syntaxcipher.estacionamento.dtos.inputs.VeiculoInput;
import com.github.syntaxcipher.estacionamento.dtos.outputs.VeiculoOutput;
import com.github.syntaxcipher.estacionamento.entites.VeiculoPatioEntity;

@Configuration
public class VeiculoPatioConvert {

	@Autowired
	private ModelMapper modelMapper;

	public VeiculoPatioEntity inputToEntity(VeiculoInput veiculoInput) {
		return modelMapper.map(veiculoInput, VeiculoPatioEntity.class);
	}

	public VeiculoOutput entityToOutput(VeiculoPatioEntity veiculoNoPatio) {
		return modelMapper.map(veiculoNoPatio, VeiculoOutput.class);
	}

	public List<VeiculoOutput> listEntityToListOutput(List<VeiculoPatioEntity> veiculosNoPatio) {
		return veiculosNoPatio.stream().map(a -> this.entityToOutput(a)).collect(Collectors.toList());
	}

}
