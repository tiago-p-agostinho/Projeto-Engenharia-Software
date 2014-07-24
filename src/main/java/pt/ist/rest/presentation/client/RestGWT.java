package pt.ist.rest.presentation.client;



import java.util.ArrayList;
import java.util.List;

import pt.ist.rest.presentation.client.LoginPage;
import pt.ist.rest.presentation.client.view.MenuOptionsPanel;
import pt.ist.rest.exception.*;
import pt.ist.rest.service.dto.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RestGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	private final Label errorMessage = new Label("");
	private LoginPage loginPage;
	private MenuPage menuPage;
	private DadosClientePage dadosClientePage;
	private RestaurantePage restaurantePage;
	private TabuleiroPage alterarQuantidadePage;
	static public List<String> cheques;

	private static final String localServerType = "ES-only";
	private static final String remoteServerType = "ES+SD";
	
	
	private final Label serverTypeLabel = new Label("Rest - local Mode");
	
	private final RestServletAsync rpcService = GWT
			.create(RestServlet.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		GWT.log("presentation.client.RestGWT::onModuleLoad() - begin");
		
		serverTypeLabel.setStyleName("h1");
		serverTypeLabel.setWidth("100%");
		
		// create label with mode type
		String serverType; // depends on type of running
		if (RootPanel.get(remoteServerType) != null) {
			GWT.log("presentation.client.Rest::onModuleLoad() running on remote mode");
			serverType = remoteServerType;
		} else { // default: local - even if it is misspelled
			GWT.log("presentation.client.Rest::onModuleLoad() running on local mode");
			serverType = localServerType;
		}
		
		
		loginPage = new LoginPage(this, rpcService);
		menuPage = new MenuPage(this, rpcService);
		restaurantePage = new RestaurantePage(this,rpcService);
		alterarQuantidadePage = new TabuleiroPage(this,rpcService);
		dadosClientePage = new DadosClientePage(this,rpcService);
		
		this.rpcService.initServer(serverType,new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
			}
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.rest::onModuleLoad()::rpcService.initBridge");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				showErrorMessage("Not able to init aplication server bridge: " + caught.getMessage());
			}
		});
		
		RootPanel updateRootPanel = RootPanel.get("labelError");
        updateRootPanel.add(errorMessage);
        errorMessage.setStyleName("labelError");
        errorMessage.setWidth("100%");
  
		showLoginPage();	
	}
	
	public void addOptionsView(final ClienteDto dto,final MenuOptionsPanel options){
		
		RootPanel formRootPanel = RootPanel.get("menuAddContainer");
		formRootPanel.clear();
		formRootPanel.add(options);
		options.setWidth("100%");
		

		options.setClickHandlerEfectuarPesquisa(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent e){
        		PratoSimpleDto p = new PratoSimpleDto(options.getPratoBox().getText());
        		p.setCustomCheck(true);
        		restaurantePage.procuraPrato(p);
        	}
        });
		options.setClickHandlerRestaurantes(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent e){
        		showRestaurantePage(dto);
        	}
        });
        options.setClickHandlerTabuleiro(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent e){
        		showTabuleiro(dto);
        	}
        });	
		options.setClickHandlerAdicionarCheques(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent e){
        		String[] arrayChecks;
        		arrayChecks = options.getChequeBox().getText().split("\\s+");
        		cheques = new ArrayList<String>();
        		for(String i : arrayChecks){
        		  cheques.add(i);
        		}
        		adicionaCheques(dto,cheques);
        		options.clearChequeBox();
        	}
        });
		options.setClickHandlerEfectuarPagamento(new ClickHandler() {
        	@Override
        	public void onClick(ClickEvent e){
        		dto.setAddNif(options.getCheckNif().getValue());
        		efectuaPagamento(dto);
        	}
        });
		options.setChangedTipoPesquisa(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent e){
				PratoSimpleDto p = new PratoSimpleDto(options.getListProcura().getValue(
														options.getListProcura().getSelectedIndex()));
				p.setCustomCheck(false);
        		restaurantePage.procuraPrato(p);
			}
		});
		options.setClickHandlerDados(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showDadosClientePage(dto);
				
			}
		});

	}
	
	public void showLoginPage() {
		this.clearPage();
		RootPanel.get("menuAddContainer").add(loginPage);
	}
	public void showMenuPage(ClienteDto loggedClient, RestauranteSimpleDto restDto) {
		this.clearPage();
		menuPage.showPage(loggedClient,restDto);
	}
	
	public void showRestaurantePage(ClienteDto loggedClient){
		this.clearPage();
		restaurantePage.showPage(loggedClient);
	}
	public void showTabuleiro(ClienteDto loggedClient){
		this.clearPage();
		alterarQuantidadePage.showPage(loggedClient);
	}
	public void showDadosClientePage(ClienteDto loggedClient){
		this.clearPage();
		dadosClientePage.showPage(loggedClient);
	}


	public void clearPage() {
		RootPanel.get("menuAddContainer").clear();
		RootPanel.get("contactsListContainer").clear();
		RootPanel.get("refresh").clear();
		RootPanel.get("logout").clear();
		errorMessage.setText("");
	}
	
	public void showErrorMessage(String message) {
		errorMessage.getElement().getStyle().setProperty("color","red");
		errorMessage.setText(message);
	}
	public void showSuccessMessage(String message) {
		errorMessage.getElement().getStyle().setProperty("color","green");
		errorMessage.setText(message);
	}

	public void adicionaCheques(ClienteDto cliente, List<String> cheques) {

		rpcService.adicionaCheques(new ChequesDto(cliente,cheques), new AsyncCallback<Void>(){
			@Override
			public void onSuccess(Void response){
				showSuccessMessage("Cheques adicionados com sucesso!");
     		}
			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof pt.ist.rest.exception.InvalidCheckException)
					showErrorMessage("Invalid check");
				else if (caught instanceof pt.ist.rest.exception.CheckAlreadyUsedException)
					showErrorMessage("Check was already used");
				else 
					showErrorMessage("Internal error: checks not added");

			}


		});
	}

	public void efectuaPagamento(ClienteDto cliente){

		rpcService.efectuaPagamento(cliente, new AsyncCallback<FaturaDto>() {
			@Override
			public void onSuccess(FaturaDto response){
				showSuccessMessage("Payment was successful: (serie:"+response.serNum
										+"seq: "+response.seqNum+")");
			}
			@Override
			public void onFailure(Throwable caught){
				if (caught instanceof NegativeBalanceException)
					showErrorMessage("Insuficient account money");
				else if (caught instanceof EmptyShoppingTrayException)
					showErrorMessage("The tray is empty");
				else if (caught instanceof ClienteInexistenteException)
					showErrorMessage("Client Nif not found");
				else if (caught instanceof EmissorInexistenteException)
					showErrorMessage("Portal nif invalid");
				else if (caught instanceof FaturaInvalidaException)
					showErrorMessage("Invalid Receipt");
				else if (caught instanceof TotaisIncoerentesException)
					showErrorMessage("The price does not match");
				else
					showErrorMessage("Internal error: payment not finished");
			}

		});

	}

  }
