package pt.ist.rest.presentation.client;



import java.util.*;

import pt.ist.rest.exception.NoRestaurantsException;
import pt.ist.rest.presentation.client.view.ListaMenuPanel;
import pt.ist.rest.presentation.client.view.ListaRestaurantesPanel;
import pt.ist.rest.presentation.client.view.MenuOptionsPanel;
import pt.ist.rest.service.dto.PratoDto;
import pt.ist.rest.service.dto.RestauranteSimpleDto;
import pt.ist.rest.service.dto.ClienteDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class MenuPage extends Composite {

	private final Button refreshButton = new Button("Refresh");
	private final Button logOutButton = new Button("Logout");

	private final ListaMenuPanel listaMenuPanel;


    private ClienteDto loggedPerson;

	private RestServletAsync rpcService;

	private RestGWT rootPage;



	public MenuPage(final RestGWT rootPage, final RestServletAsync rpcService) {				
		this.rpcService = rpcService;
		this.rootPage = rootPage;

		listaMenuPanel = new ListaMenuPanel(rpcService, rootPage);
		
		refreshButton.setStyleName("refreshListButton");
		logOutButton.setStyleName("logoutListButton");
		logOutButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            	
            	rootPage.showLoginPage();
            }
	});


    }
	public void setRefreshHandler(final RestauranteSimpleDto restDto){
		refreshButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				rootPage.showMenuPage(loggedPerson,restDto);
			}
		});
		
	}


	public void listaMenu(final RestauranteSimpleDto dto) {
		rpcService.listaMenu(dto, new AsyncCallback<List<PratoDto>>() {
			@Override
			public void onSuccess(List<PratoDto> lista) {
				final RootPanel listRootPanel = RootPanel.get("contactsListContainer");	
				listRootPanel.clear(); 
				listRootPanel.add(listaMenuPanel);
				listaMenuPanel.clearMenu();
				listaMenuPanel.setRestaurante(dto);
				for(PratoDto p : lista){
					listaMenuPanel.add(p);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("presentation.client.view::ContactListPanel.rpcService.removeContact");
				GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
				rootPage.showErrorMessage("Not able to show : " + dto.getNome()+ "'s menu. Error: " + caught);
			}
		});
	}


	public	void showPage(final ClienteDto loggedClient,RestauranteSimpleDto restDto) {
		this.loggedPerson = loggedClient;

		listaMenuPanel.setLoggedPerson(loggedPerson);
		
		this.rootPage.addOptionsView(loggedClient,new MenuOptionsPanel(this.rootPage));
		this.setRefreshHandler(restDto);
		this.listaMenu(restDto);

		RootPanel refreshRootPanel = RootPanel.get("refresh");
		refreshRootPanel.add(refreshButton);

		RootPanel logoutRootPanel = RootPanel.get("logout");
		logoutRootPanel.add(logOutButton);

	}

}
