package com.leomara.cursomc.services;

import com.leomara.cursomc.domain.Pedido;
import com.leomara.cursomc.repositories.PedidoRepository;
import com.leomara.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repo;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: "+id+", Tipo: "+ Pedido.class.getName()
        ));
    }
}
