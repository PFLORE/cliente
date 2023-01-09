package com.flores.service;

import java.util.ArrayList;
import java.util.LinkedList;
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
	
	public List<Cliente> listar() {
		List<Cliente> clientes = new LinkedList<>();
		
		repository.findAll().forEach(clientes::add);
		
		return clientes;
	}
	
	public List<Cliente> buscarPorNombre(String nombre) {
		List<Cliente> clientes = new ArrayList<>();
		repository.findByNombresContaining(nombre).forEach(clientes::add);
		return clientes;
	}
	
	public Optional<Cliente> obtenerPorId(Integer id) {
		
		Optional<Cliente> _cliente = repository.findById(id);
		
		return _cliente;
	}
	
	public List<ClienteDto> ObtenerMayorA(Integer valor) {
		
		List<ClienteDto> _lista = repository.findAll()
				.stream().filter(x -> x.getEdad() > valor)
				.map(this::convertToDto)
				.collect(Collectors.toList());
		
		return _lista;
	}
	
	public Cliente crear(Cliente cliente) {
		Cliente _cliente = repository.save(cliente);
		return _cliente;
	}
	
	public void eliminarPorId(Integer id) {
		repository.deleteById(id);
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
