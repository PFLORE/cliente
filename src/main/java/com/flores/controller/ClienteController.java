package com.flores.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flores.dto.ClienteDto;
import com.flores.dto.MensajeDto;
import com.flores.entity.Cliente;
import com.flores.service.ClienteService;
import com.flores.util.Utilidades;

@RestController
@RequestMapping(path = "cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/listar")
	public ResponseEntity<List<ClienteDto>> listar(@RequestParam(required = false) String nombre) {
		
		List<ClienteDto> clientes = new LinkedList<>();
		
		if(nombre == null) {
			service.listar().stream()
				.map(service::convertToDto)
				.forEach(clientes::add);
		} else {
//			service.listar().stream()
//				.filter(x -> x.getNombres().contains(nombre))
//				.map(service::convertToDto)
//				.forEach(clientes::add);
			
			service.buscarPorNombre(nombre).stream()
				.map(service::convertToDto)
				.forEach(clientes::add);
		}
		
		if(clientes.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity(new MensajeDto("Datos encontrados", clientes), HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/obtener/{id}")
	public ResponseEntity<ClienteDto> obtenerPorId(@PathVariable("id") String id) throws Exception {
		String _idStr = Utilidades.Desencriptar(id);
		Integer _id = Integer.parseInt(_idStr);
		
		ClienteDto _dto = service.obtenerPorId(_id).map(service::convertToDto).orElse(null);
		
		if(_dto != null) {
			return new ResponseEntity(new MensajeDto("Cliente encontrado", _dto), HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/buscar/mayorA/{valor}")
	public ResponseEntity<ClienteDto> obtenerMayorA(@PathVariable("valor") Integer valor) {
		List<ClienteDto> _lista = service.ObtenerMayorA(valor);
		
		if(_lista.size() != 0) {
			return new ResponseEntity(new MensajeDto("Clientes encontrados", _lista) , HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(path = "/crear")
	public ResponseEntity<?> crear(@Valid @RequestBody ClienteDto clienteDto, BindingResult result) {
		try {
			
			if (result.hasErrors()) {
	            return Utilidades.validarCampos(result);
	        }
			
			Cliente _entity = service.convertToEntity(clienteDto);
			Cliente _cliente = service.crear(_entity);
			
			ClienteDto _dto = service.convertToDto(_cliente);
			
			return new ResponseEntity(new MensajeDto("Cliente registado", _dto), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(path = "/actualizar/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody ClienteDto clienteDto, 
			@PathVariable("id") String id, BindingResult result) throws Exception {
		
		String _idStr = Utilidades.Desencriptar(id);
		Integer _id = Integer.parseInt(_idStr);
		
		if (result.hasErrors()) {
            return Utilidades.validarCampos(result);
        }
		
		ClienteDto _dtoResponse = service.obtenerPorId(_id).map(service::convertToDto).orElse(null);
		
		if(_dtoResponse != null) {
			Cliente _entity = service.convertToEntity(clienteDto);
			Cliente _cliente = service.crear(_entity);
			
			ClienteDto _dto = service.convertToDto(_cliente);
			
			return new ResponseEntity(new MensajeDto("Cliente actualizado", _dto), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(path = "/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") String id) throws Exception {
		
		String _idStr = Utilidades.Desencriptar(id);
		Integer _id = Integer.parseInt(_idStr);
		
		service.eliminarPorId(_id);
		
		return new ResponseEntity(new MensajeDto("Cliente eliminado"), HttpStatus.OK);
	}
}
