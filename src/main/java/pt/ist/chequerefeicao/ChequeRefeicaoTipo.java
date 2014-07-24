package pt.ist.chequerefeicao;

import java.util.List;

public interface ChequeRefeicaoTipo {
	
	public abstract int cashChecks(String payee,List<String> checks) throws CheckAlreadyUsedException, InvalidCheckException;

}
