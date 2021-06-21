package com.example.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {

	static Logger logger = Logger.getLogger(ClienteService.class);

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClientEntity> listar() {
		return clienteRepository.findAll();
	}

	public ClientEntity save(ClientEntity clientEntity) {
		try {
			if (clientEntity.getShareKey() != null) {
				logger.info(" registro realizado exitosamente");
				return clienteRepository.save(clientEntity);
			}
		} catch (Exception e) {
			logger.error("Registro duplicado " + clientEntity.getShareKey());
			return null;
		}

		return null;
	}

	public List<ClientEntity> findShareKey(String shareKey) {
		return clienteRepository.findByShareKeyContaining(shareKey);
	}

	public Boolean deleteById(Integer id) {
		try {
			logger.warn(" registro con id: " + id + " eliminado exitosamente");
			clienteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage() + " registro con: " + id + " eliminado");
		}
		return false;
	}

	public ClientEntity update(ClientEntity entity) {
		Optional<ClientEntity> cliente = clienteRepository.findById(entity.getId());
		if (cliente.isPresent()) {
			logger.warn("Registro con: " + entity.getId() + "actualizado con éxito");
			return clienteRepository.save(entity);
		}
		logger.warn("Registro con: " + entity.getId() + "fallo al actualizar");
		return null;
	}

	public Optional<ClientEntity> findbyid(Integer id) {
		return clienteRepository.findById(id);
	}

	public List<ClientEntity> findByPrueba(String sharekey, String phone, String email, Date dataStart, Date dateEnd) {
		return clienteRepository.findByPrueba(sharekey, phone, email, dataStart, dateEnd);
	}

	public Integer contar() {
		return (int) clienteRepository.count();
	}

	public Page<ClientEntity> paginar(Integer page, Integer size) {
		Pageable paging = PageRequest.of(page, size);
		Page<ClientEntity> pagedResult = clienteRepository.findAll(paging);
		return pagedResult;
	}

}
