package com.leomara.cursomc.services.validation;

import com.leomara.cursomc.domain.Cliente;
import com.leomara.cursomc.domain.enums.TipoCliente;
import com.leomara.cursomc.dto.ClienteNewDTO;
import com.leomara.cursomc.repositories.ClienteRepository;
import com.leomara.cursomc.resources.exceptions.Validation.FieldMessage;
import com.leomara.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));

        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

        Cliente aux = repository.findByEmail(objDto.getEmail());
        if (aux != null)
            list.add(new FieldMessage("email", "Email já existente"));

        for (FieldMessage e: list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
