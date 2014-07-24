package pt.ist.rest.service;

import java.util.Set;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.rest.domain.*;
import junit.framework.TestCase;
import jvstm.Atomic;


public abstract class RestServiceTestCase extends TestCase {
	static {
		if(FenixFramework.getConfig()==null) {
			FenixFramework.initialize(new Config() {{
				domainModelPath="src/main/dml/rest.dml";
				dbAlias = "//localhost:3306/restdb";
				dbUsername = "rest";
				dbPassword = "r3st";
				rootClass=Rest.class;
			}});
		}
	}

	protected RestServiceTestCase(String msg) {
		super(msg);
	}

	protected RestServiceTestCase() {
		super();
	}

	protected void setUp() {
		cleanRest();
	}	

	protected void tearDown() {
		cleanRest();
	}


	@Atomic
	protected void cleanRest() {
		Rest rest = FenixFramework.getRoot();
		Set<Restaurante> Restaurantes = rest.getRestauranteSet();
		Restaurantes.clear();
		Set<Cliente> Clientes = rest.getClienteSet();
		Clientes.clear();
	}

	private Restaurante getRestaurante(String nomeRestaurante) {
		Rest rest = FenixFramework.getRoot();
		return rest.procuraRestaurantePorNome(nomeRestaurante);
	}

	@Atomic
	protected boolean verificaRestaurante(String nomeRestaurante) {
		return getRestaurante(nomeRestaurante) != null;
	}

	@Atomic
	protected boolean verificaPrato(String nomeRestaurante, String nomePrato) {
		Restaurante restaurante = getRestaurante(nomeRestaurante);
		return (restaurante != null && restaurante.hasPrato(restaurante.procuraPrato(nomePrato)));
	}

	private Prato getPrato(String nomePrato,String nomeRestaurante) {
		Restaurante restaurante = getRestaurante(nomeRestaurante);
		return restaurante.procuraPrato(nomePrato);
	}

	@Atomic
	protected boolean verificaItem(String clientName, String nomePrato,String nomeRestaurante) {
		Cliente cliente = getCliente(clientName);
		Prato prato = getPrato(nomePrato,nomeRestaurante);
		return (cliente != null && cliente.getCompraAberta().compraTemPrato(prato));
	}

	private Cliente getCliente(String userCli) {
		Rest rest = FenixFramework.getRoot();
		return rest.procuraClientePorNome(userCli);
	}

	@Atomic
	protected boolean verificaCliente(String userCliente) {
		Cliente cliente = getCliente(userCliente);
		return(cliente != null);
	}

	@Atomic
	protected void addItem(String nomeRestaurante, String userCli, String nomePrato, int quantidade) {
		Cliente cliente = getCliente(userCli);
		Restaurante restaurante = getRestaurante(nomeRestaurante);
		Prato prato = restaurante.procuraPrato(nomePrato);
		cliente.adicionaItemACompra(prato, quantidade);
	}

	@Atomic
	protected void adicionaCliente(String nomeCli,String morada,String user,String mail,String pass,Integer nif) {
		Rest rest = FenixFramework.getRoot();
		rest.addCliente(new Cliente(nomeCli,morada,user,mail,pass,nif));
	}


	@Atomic
	protected void adicionaRestaurante(String nomeRestaurante, String moradaRestaurante) {
		Rest rest = FenixFramework.getRoot();
		rest.addRestaurante(new Restaurante(nomeRestaurante, moradaRestaurante));
	}

	@Atomic
	protected void addPrato(String nomeRestaurante, String nomePrato, Integer preco, Integer calorias, Integer IDPrato) {
		Restaurante restaurante = getRestaurante(nomeRestaurante);
		restaurante.adicionaPrato(new Prato(nomePrato, preco, calorias, IDPrato));
	}

	@Atomic
	protected int getNumeroPratos(String nomeRestaurante) {
		return getRestaurante(nomeRestaurante).getPratoCount();
	}

	@Atomic
	protected int getNumberOfItems(String userCli) {
		return getCliente(userCli).getCompraAberta().getItemCount();
	}
	
	@Atomic
	protected int verificaItemQuantidade(String clientName, String nomePrato,String nomeRestaurante) {
		Cliente cliente = getCliente(clientName);
		Prato prato = getPrato(nomePrato,nomeRestaurante);
		return cliente.getCompraAberta().getItemPorPrato(prato).getQuantidade();
	}
}
