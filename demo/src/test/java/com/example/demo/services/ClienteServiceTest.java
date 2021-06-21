package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.ClienteRepository;

@SpringBootTest
public class ClienteServiceTest {
	Date fecha = new Date();
	List<ClientEntity> listOne = new ArrayList<>();
	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteService clienteService = new ClienteService();

	@BeforeEach
	public void setup() {
		ClientEntity clienteOne = new ClientEntity(0, "A", "A", "A", "A", fecha, 1);
		listOne.add(clienteOne);
		ClientEntity clienteTwo = new ClientEntity(1, "B", "B", "B", "B", fecha, 1);
		listOne.add(clienteTwo);

		when(clienteRepository.count()).thenReturn((long) 2);
		when(clienteRepository.findAll()).thenReturn(listOne);
	}

	/*
	 * @DisplayName("Test count registers")
	 * 
	 * @Test void testGet() { long cantidad = 2; long salida =
	 * clienteService.contar().longValue(); assertEquals(cantidad, salida); }
	 */

	@DisplayName("Test List Clients")
	@Test
	void listar() {
		Integer cantidadElementos = 2;
		Integer cantidadLista = clienteService.listar().size();
		assertNotNull(clienteService.listar());
		assertEquals(cantidadElementos, cantidadLista);
	}

	@DisplayName("Test Save Clients with parameters ok")
	@Test
	void SaveClient() {
		ClientEntity clienteOne = new ClientEntity(0, "A", "A", "A", "A", fecha, 1);
		ClientEntity clienteTwo = new ClientEntity(1, "B", "B", "B", "B", fecha, 1);
		when(clienteRepository.save(clienteOne)).thenReturn(clienteTwo);
		ClientEntity salida = (clienteService.save(clienteOne));
		assertNotNull(salida);
	}

	@DisplayName("Save Client When Id Is Zero And ShareKey Is Null")
	@Test
	void SaveClientWhenIdIsZeroAndShareKeyIsNull() {
		ClientEntity clienteOne = new ClientEntity(0, null, "A", "A", "A", fecha, 1);
		when(clienteRepository.save(clienteOne)).thenReturn(null);
		ClientEntity salida = clienteService.save(clienteOne);
		assertNull(salida);
	}

	@DisplayName("Save Client When Id Is diff Zero And ShareKey Is Null")
	@Test
	void SaveClientWhenIdIsDiffZeroAndShareKeyIsNull() {
		ClientEntity clienteOne = new ClientEntity(1, null, "A", "A", "A", fecha, 1);
		when(clienteRepository.save(clienteOne)).thenReturn(null);
		ClientEntity salida = clienteService.save(clienteOne);
		assertNull(salida);
	}

	@DisplayName("Save Client When Id Is diff Zero And ShareKey Is Diff Null")
	@Test
	void SaveClientWhenIdIsZeroAndShareKeyIsDiffNull() {
		ClientEntity clienteOne = new ClientEntity(1, "A", "A", "A", "A", fecha, 1);
		when(clienteRepository.save(clienteOne)).thenReturn(null);
		ClientEntity salida = clienteService.save(clienteOne);
		assertNull(salida);
	}

	@DisplayName("Search Client By Id when Id Is  Not Null")
	@Test
	void SearchClientByIdwhenIdIsNotNull() {
		Integer id = 1;
		ClientEntity clienteOne = new ClientEntity(1, "A", "A", "A", "A", fecha, 1);
		when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteOne));
		Optional<ClientEntity> salida = clienteService.findbyid(id);
		assertNotNull(salida);
		assertEquals(salida.get().getId(), id);
	}

	@DisplayName("Search Client By Id when Id Is Null")
	@Test
	void SearchClientByIdwhenIdIsNull() {
		Integer id = null;
		ClientEntity clienteOne = new ClientEntity(1, "A", "A", "A", "A", fecha, 1);
		when(clienteRepository.findById(id)).thenReturn(null);
		Optional<ClientEntity> salida = clienteService.findbyid(id);
		assertNull(salida);
	}

	@SuppressWarnings({})
	@DisplayName("Page of client")
	@Test
	void PagesClient() {
		Pageable paging = PageRequest.of(0, 1);
		ClientEntity clienteOne = new ClientEntity(0, "A", "A", "A", "A", fecha, 1);
		listOne.add(clienteOne);
		Page<ClientEntity> mipage = new PageImpl<>(listOne, paging, listOne.size());
		when((Page<ClientEntity>) clienteRepository.findAll(paging)).thenReturn(mipage);
		Page<ClientEntity> paginaresultado = clienteService.paginar(0, 1);
		assertEquals(paginaresultado.getTotalElements(), mipage.getTotalElements());
	}

}
