package com.flores.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flores.dto.ClienteDto;
import com.flores.entity.Cliente;
import com.flores.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Optional<Cliente> obtenerPorId(Integer id) {
		Optional<Cliente> _cliente = repository.findById(id);
		return _cliente;
	}
	
	public List<ClienteDto> ObtenerMayorA(Integer valor) {
		List<ClienteDto> _lista = repository.findByMayorA(valor)
				.stream().map(this::convertToDto)
				.collect(Collectors.toList());
		return _lista;
	}
	
	public Cliente crear(Cliente cliente) {
		Cliente _cliente = repository.save(cliente);
		return _cliente;
	}
	
	public Cliente convertToEntity(ClienteDto post) {
		Cliente entity = modelMapper.map(post, Cliente.class);
		return entity;
	}
	
	public ClienteDto convertToDto(Cliente post) {
		ClienteDto dto = modelMapper.map(post, ClienteDto.class);
		
		return dto;
	}
}
