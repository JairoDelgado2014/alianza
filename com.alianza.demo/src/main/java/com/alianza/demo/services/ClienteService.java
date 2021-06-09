package com.alianza.demo.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alianza.demo.entity.ClientEntity;
import com.alianza.demo.repository.ClienteRepository;

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

	/*public List<ClientEntity> findByPrueba(String sharekey, String phone, String email, Date dataStart, Date dateEnd) {		
		return clienteRepository.findByPrueba(sharekey, phone, email, dataStart, dateEnd);
	}*/

}
