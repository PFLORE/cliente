package com.flores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
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
	
	@Mock
	private ModelMapper modelMapper;
	
	@Test
	@DisplayName("buscar cliente por id")
    public void buscarClientePorId() {
		
		Cliente _cliente1 = new Cliente(1, "Cliente 1", 27);
		Cliente _cliente2 = new Cliente(2, "Cliente 1", 27);
		
		when(repository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(_cliente1));
        
        assertEquals(service.obtenerPorId(1).get().getEdad(), _cliente2.getEdad());
    }

}
