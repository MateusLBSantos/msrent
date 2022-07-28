package com.mateus.msrent.services;



import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateus.msrent.dto.ClientDTO;
import com.mateus.msrent.entities.Client;
import com.mateus.msrent.repositories.ClientRepository;
import com.mateus.msrent.services.exceptions.DataBaseException;
import com.mateus.msrent.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged (PageRequest pageRequest) {
		
		Page<Client> list = repository.findAll(pageRequest);
		
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não existente"));
		
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copiaDtoParaEntity(dto, entity);
		entity = repository.save(entity);
		
		return new ClientDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não existe " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integridade violada");
		}
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copiaDtoParaEntity(dto, entity);
			entity = repository.save(entity);
			
			return new ClientDTO(entity);
		}
		catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}
	
	private void copiaDtoParaEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}

}
