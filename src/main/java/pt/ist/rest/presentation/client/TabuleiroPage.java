package pt.ist.rest.presentation.client;

import java.util.List;

import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.exception.EmptyShoppingTrayException;
import pt.ist.rest.presentation.client.view.ListaTabuleiroPanel;
import pt.ist.rest.presentation.client.view.MenuOptionsPanel;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.TabuleiroDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class TabuleiroPage extends Composite{


		private final Button refreshButton = new Button("Refresh");
		private final Button logOutButton = new Button("Logout");
		
	    private ClienteDto loggedPerson;
		private RestServletAsync rpcService;
		private RestGWT rootPage;
		
		private final ListaTabuleiroPanel alterarQuantidadePanel;
		
		public TabuleiroPage(final RestGWT rootPage, final RestServletAsync rpcService) {				
			this.rpcService = rpcService;
			this.rootPage = rootPage;

			alterarQuantidadePanel = new ListaTabuleiroPanel(rootPage, rpcService);
			
			refreshButton.setStyleName("refreshListButton");

			logOutButton.setStyleName("logoutListButton");
			refreshButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					rootPage.showTabuleiro(loggedPerson);
				}
			});
			logOutButton.addClickHandler(new ClickHandler() {
	            @Override
	            public void onClick(ClickEvent event) {
	            	
	            	rootPage.showLoginPage();
	            }
		
			});

		
			
	    }
		
		void showPage(final ClienteDto loggedClient) {
			this.loggedPerson = loggedClient;

			alterarQuantidadePanel.setLoggedPerson(loggedPerson);

			this.rootPage.addOptionsView(loggedClient,new MenuOptionsPanel(this.rootPage));

			this.listaTabuleiro();
			alterarQuantidadePanel.setWidth("100%");

			RootPanel refreshRootPanel = RootPanel.get("refresh");
			refreshRootPanel.add(refreshButton);

			RootPanel logoutRootPanel = RootPanel.get("logout");
			logoutRootPanel.add(logOutButton);
			
			
		}

		
		
		  public void listaTabuleiro(){
				rpcService.listaTabuleiro(loggedPerson,new AsyncCallback<TabuleiroDto>() {
					
					public void onSuccess(TabuleiroDto response) {
						alterarQuantidadePanel.clearList();
						final RootPanel listRootPanel = RootPanel.get("contactsListContainer");
						listRootPanel.clear();
						listRootPanel.add(alterarQuantidadePanel);
						for(ItemDto i : response.getItems()){
							alterarQuantidadePanel.add(i);
						}
					}

					public void onFailure(Throwable caught) {
						GWT.log("presentation.client.AlterarQuantidadePage::listaTabuleiro()::rpcService.listaTabuleiro()");
						GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
						if (caught instanceof EmptyShoppingTrayException) {
							rootPage.showErrorMessage("No items found");
						} else if (caught instanceof ClientNotFoundException) {
							Window.alert("INTERNAL ERROR: Client not found");
						} else{
							Window.alert("ERROR: Cannot show Tray: " + caught.getMessage());
						}
					}
				});
			}
		  
		
		class MyHandler {
			// Send the request to server
			protected void sendRequestToServer() {
				listaTabuleiro();
			}
		}

		// Create a handler for clicks
		class MyClickHandler extends MyHandler implements ClickHandler {
			// Fired when the user clicks on the related button
			public void onClick(ClickEvent event) {
				sendRequestToServer();
			}
		}

		
		
	}
