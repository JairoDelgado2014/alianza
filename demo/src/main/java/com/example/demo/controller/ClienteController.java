package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ClientEntity;
import com.example.demo.services.ClienteService;
import com.example.demo.vo.ClientVo;

@CrossOrigin()
@RestController
@RequestMapping("client")
public class ClienteController {

	@Autowired
	private ClienteService clientService;

	@GetMapping("/listar")
	public List<ClientEntity> listar() {
		return clientService.listar();
	}

	@PostMapping("/guardar")
	public ClientEntity saveClient(@RequestBody ClientEntity clientEntity) {
		return clientService.save(clientEntity);
	}

	@GetMapping("/buscarsharekey/{shareKey}")
	public List<ClientEntity> findShareKey(@PathVariable("shareKey") String shareKey) {
		return clientService.findShareKey(shareKey);
	}

	@PutMapping("/editar")
	public ClientEntity updateClient(@RequestBody ClientEntity entity) {
		return clientService.update(entity);
	}

	@DeleteMapping("/eliminar/{id}")
	public Boolean deleteClient(@PathVariable("id") Integer id) {
		return clientService.deleteById(id);
	}

	@GetMapping("/buscarid/{id}")
	public Optional<ClientEntity> findbyid(@PathVariable("id") Integer id) {
		return clientService.findbyid(id);
	}

	@PostMapping("/advance")
	public List<ClientEntity> findbyid(@RequestBody ClientVo clientVo) {
		String sharekey = clientVo.getShareKey();
		String phone = clientVo.getPhone();
		String email = clientVo.getEmail();
		Date dateStart = clientVo.getDateStart();
		Date dateEnd = clientVo.getDateEnd();
		return clientService.findByPrueba(sharekey, phone, email, dateStart, dateEnd);
	}

	@GetMapping("/paginas/{page}/{size}")
	public Page<ClientEntity> getPaginatedCountries(@PathVariable int page, @PathVariable int size) {
		return clientService.paginar(page, size);
	}
}
