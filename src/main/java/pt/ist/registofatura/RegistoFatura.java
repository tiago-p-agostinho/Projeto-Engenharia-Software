package pt.ist.registofatura;

import pt.ist.registofatura.ws.*;
import pt.ist.registofatura.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

public class RegistoFatura {

	private static RegistoFaturaPortType registoFatura = null;

	

	public static void setRegistoFatura(RegistoFaturaPortType registo){
		registoFatura = registo;
	}

	public static Serie pedirSerie(int nifEmissor) throws EmissorInexistente_Exception{
		return registoFatura.pedirSerie(nifEmissor);
	}

	public static void comunicarFatura(Fatura fatura) throws ClienteInexistente_Exception, EmissorInexistente_Exception,
							FaturaInvalida_Exception, TotaisIncoerentes_Exception {
		registoFatura.comunicarFatura(fatura);
	}

	public static List<Fatura> listarFacturas(Integer nifEmissor, Integer nifCliente) throws ClienteInexistente_Exception,
										    											EmissorInexistente_Exception{
		return registoFatura.listarFacturas(nifEmissor,nifCliente);
	}

	public static int consultarIVADevido(int nifEmissor, XMLGregorianCalendar ano) throws EmissorInexistente_Exception {
		return registoFatura.consultarIVADevido(nifEmissor,ano);
	}

}
