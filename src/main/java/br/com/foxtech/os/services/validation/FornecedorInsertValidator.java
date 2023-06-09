package br.com.foxtech.os.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.foxtech.os.domain.Fornecedor;
import br.com.foxtech.os.domain.enums.TipoCliente;
import br.com.foxtech.os.dto.FornecedorNewDTO;
import br.com.foxtech.os.repositories.FornecedorRepository;
import br.com.foxtech.os.resources.exception.FieldMessage;
import br.com.foxtech.os.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FornecedorInsertValidator implements ConstraintValidator<FornecedorInsert, FornecedorNewDTO> {

	@Autowired
	private FornecedorRepository repo;
	@Override
	public void initialize(FornecedorInsert ann) {
	}

	@Override
	public boolean isValid(FornecedorNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {

			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Fornecedor aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}