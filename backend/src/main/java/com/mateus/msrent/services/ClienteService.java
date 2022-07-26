package com.mateus.msrent.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateus.msrent.dto.ClienteDTO;
import com.mateus.msrent.entities.Cliente;
import com.mateus.msrent.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged (PageRequest pageRequest) {
		
		Page<Cliente> list = repository.findAll(pageRequest);
		
		return list.map(x -> new ClienteDTO(x));
	}

}
