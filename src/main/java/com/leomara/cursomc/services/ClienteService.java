package com.leomara.cursomc.services;

import com.leomara.cursomc.domain.Cliente;
import com.leomara.cursomc.repositories.ClienteRepository;
import com.leomara.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+ id + ", Tipo: "+ Cliente.class.getName()
        ));
    }
}
