package br.com.foxtech.os.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.foxtech.os.domain.Fornecedor;
import br.com.foxtech.os.dto.FornecedorNewDTO;
import br.com.foxtech.os.repositories.FornecedorRepository;
import br.com.foxtech.os.resources.exception.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FornecedorUpdateValidator implements ConstraintValidator<FornecedorUpdate, FornecedorNewDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private FornecedorRepository repo;

	@Override
	public void initialize(FornecedorUpdate ann) {
	}

	@Override
	public boolean isValid(FornecedorNewDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Fornecedor aux = repo.findByEmail(objDto.getEmail());

		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}