package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;

import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.domain.Rest;
import pt.ist.rest.domain.Prato;
import pt.ist.rest.exception.DishesNotFoundException;
import pt.ist.rest.service.dto.PratoDeRestauranteDto;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.PratoSimpleDto;
import pt.ist.rest.service.dto.PratosDto;


public abstract class ProcuraPratoService extends RestService {
	private PratoSimpleDto dto;
	private PratosDto result;

	public ProcuraPratoService(PratoSimpleDto dto) {
		this.dto = dto;
	}

	public final void dispatch()throws DishesNotFoundException{
		List<PratoDeRestauranteDto> pratosDto = new ArrayList<PratoDeRestauranteDto>();
		List<Prato> pratos = procura(dto.getAtributo());
		for (Prato p: pratos){
			pratosDto.add(new PratoDeRestauranteDto(p.getNome(),p.getCalorias(),
				p.getPreco(),p.calculaClassificacao(),p.getRestaurante().getNome()));
		}
		result = new PratosDto(pratosDto);
	}

	public abstract List<Prato> procura(String atributo);

	public PratosDto getResult(){
		return this.result;
	} 
}