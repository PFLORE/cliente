package com.flores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flores.entity.Cliente;
import com.flores.repository.ClienteRepository;
import com.flores.service.ClienteService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ClienteApplicationTests {

	@InjectMocks
	private ClienteService service;
	
	@Mock
	private ClienteRepository repository;
	
	@Test
	@DisplayName("buscar cliente por id valido")
    public void when_idIsValid_expect_client() {
		
		Cliente _cliente1 = new Cliente(1, "Pedro", 27);
		
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(_cliente1));
        
		Cliente _cliente2 = service.obtenerPorId(1).get();
		
		assertNotNull(_cliente2);
        assertEquals("Pedro", _cliente2.getNombres());
    }
	
	@Test
	@DisplayName("Listar todos los clientes")
	public void when_expect_allClients() {
		
		Cliente _cliente = new Cliente(1, "Juan", 25);
		
		when(repository.findAll()).thenReturn(Arrays.asList(_cliente));
		
		List<Cliente> _clientes = service.listar();
		
		assertNotNull(_clientes);
		assertEquals(1, _clientes.size());
		assertEquals("Juan", _clientes.get(0).getNombres());
	}

}
