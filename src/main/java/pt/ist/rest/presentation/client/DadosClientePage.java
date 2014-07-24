package pt.ist.rest.presentation.client;

import pt.ist.rest.presentation.client.view.DadosClientePanel;
import pt.ist.rest.presentation.client.view.ListaMenuPanel;
import pt.ist.rest.presentation.client.view.MenuOptionsPanel;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.RestauranteSimpleDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class DadosClientePage {
	
	private final DadosClientePanel dadosClientePanel;
	   private ClienteDto loggedPerson;
		private RestServletAsync rpcService;
		private RestGWT rootPage;

		private final Button refreshButton = new Button("Refresh");
		private final Button logOutButton = new Button("Logout");
		
		public DadosClientePage(final RestGWT rootPage, final RestServletAsync rpcService) {				
			this.rpcService = rpcService;
			this.rootPage = rootPage;

			dadosClientePanel = new DadosClientePanel(rootPage, rpcService);
			
			refreshButton.setStyleName("refreshListButton");

			logOutButton.setStyleName("logoutListButton");
			refreshButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					rootPage.showRestaurantePage(loggedPerson);
				}
			});
			logOutButton.addClickHandler(new ClickHandler() {
	            @Override
	            public void onClick(ClickEvent event) {
	            	
	            	rootPage.showLoginPage();
	            }
		
			});

	    }
	
	public void mostraDadosCliente(final ClienteDto dto) {
		rpcService.mostraDadosCliente(dto, new AsyncCallback<ClienteDto>() {
			@Override
			public void onSuccess(ClienteDto cliente) {
				final RootPanel listRootPanel = RootPanel.get("contactsListContainer");	
				listRootPanel.clear(); 
				listRootPanel.add(dadosClientePanel);
				dadosClientePanel.clear();
				dadosClientePanel.add(cliente);
			
			
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.view::ContactListPanel.rpcService.removeContact");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				rootPage.showErrorMessage("Not able to show : " + dto.getNome()+ ". Error: " + caught);
			}
		});
	}
	
	public	void showPage(final ClienteDto loggedClient) {
		this.loggedPerson = loggedClient;

		dadosClientePanel.setLoggedPerson(loggedPerson);
		
		this.rootPage.addOptionsView(loggedClient, new MenuOptionsPanel(this.rootPage));
		this.mostraDadosCliente(loggedPerson);
		
		RootPanel refreshRootPanel = RootPanel.get("refresh");
		refreshRootPanel.add(refreshButton);

		RootPanel logoutRootPanel = RootPanel.get("logout");
		logoutRootPanel.add(logOutButton);

	}
	
}
