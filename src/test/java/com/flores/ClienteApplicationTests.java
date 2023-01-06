package com.flores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.flores.dto.ClienteDto;
import com.flores.service.ClienteService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ClienteApplicationTests {

	@Autowired
	private ClienteService service;
	
	@Test
    public void buscarMayorA_edadValida_retornaClientes() {
        List<ClienteDto> _clientes = service.ObtenerMayorA(20);

        assertThat(_clientes)
                .isNotEmpty()
                .allMatch(cliente -> cliente.getEdad() > 20);
    }

    @Test
    public void buscarMayorA_edadNull_lanzaExcepcion() {
        assertThatIllegalArgumentException().isThrownBy(()
                -> service.ObtenerMayorA(null))
                .withMessage("La edad indicada es inválida");
    }

}
