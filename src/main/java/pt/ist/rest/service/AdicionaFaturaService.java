package pt.ist.rest.service;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.exception.*;
import pt.ist.rest.domain.*;
import pt.ist.rest.service.dto.*;
import pt.ist.registofatura.*;
import pt.ist.registofatura.ws.*;


public class AdicionaFaturaService extends RestService {

	final private Fatura fatura = new Fatura();
	final private ClienteDto cliente;
	final private int IVA = 23;

	public AdicionaFaturaService(ClienteDto cliente, Serie serie, int sequencia) {

		this.cliente = cliente;

		this.fatura.setNumSeqFatura(sequencia);
		this.fatura.setData(serie.getValidoAte());
		this.fatura.setNumSerie(serie.getNumSerie());
	}

	public final void dispatch() throws ClientNotFoundException{

		Rest rest = FenixFramework.getRoot();

		final Cliente cliente = rest.procuraClientePorNome(this.cliente.getUser());
		
		if (this.cliente.addNif())
			this.fatura.setNifCliente(cliente.getNif());
		else this.fatura.setNifCliente(null);

		this.fatura.setIva(IVA);
		cliente.getCompraAberta().retiraFatura(fatura);

		this.fatura.setNomeEmissor(rest.getNome());
this.fatura.setNifEmissor(rest.getNif());
		
	}

	public final Fatura getResult(){
		return this.fatura;
	}

}

		


	
	
