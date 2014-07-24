package pt.ist.rest.presentation.client.view;


import pt.ist.rest.presentation.client.RestGWT;
import pt.ist.rest.presentation.client.RestServletAsync;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.exception.NoRestaurantsException;

import com.google.gwt.user.client.Window;

import pt.ist.rest.service.dto.RestauranteDto;
import pt.ist.rest.service.dto.RestauranteSimpleDto;
import pt.ist.rest.service.dto.PratoDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.core.client.GWT;

import java.util.*;

public class ListaRestaurantesPanel extends FlexTable {

	private final RestServletAsync rpcService;
	private ClienteDto loggedPerson;
	private RestGWT rootPage;


	public ListaRestaurantesPanel(RestServletAsync rpcService, RestGWT rootPage) {
		GWT.log("presentation.client.view.ContactListPanel::constructor()");
		// set RPC service
		this.rpcService = rpcService;
		// format table main features:
		addStyleName("contactsTable");
		// add header row:
		setText(0, 0, "Nome");
		setText(0, 1, "Morada");
		setText(0, 2, "Classificacao");
		setText(0, 3, "Menu");

		// add style to row:
		getRowFormatter().addStyleName(0, "contactsTableHeader");
		//
		this.rootPage = rootPage;
	}

	public final void setLoggedPerson(ClienteDto dto) {
		this.loggedPerson = dto;
	}

	public void clearRestaurantList() {
		GWT.log("presentation.client.view.ContactListPanel::clearRestaurantList()");
		int rowCount = getRowCount();

		for (int i = rowCount - 1; i > 0; i--)
			removeRow(i);
	}

	public void add(RestauranteSimpleDto restaurant) {
		GWT.log("presentation.client.view.ContactListPanel::add(" + restaurant + ")");
		// get the number of the next row:
		int row = getRowCount();

		// add name and phone number (and set style from CSS)
		setText(row, 0, restaurant.getNome());
		setText(row, 1, restaurant.getMorada());
		setText(row, 2, Integer.toString(restaurant.getClassificacao()));

		// if we want styles across columns (and data type):
		getCellFormatter().addStyleName(row, 0, "contactsTableNameCell");
		getCellFormatter().addStyleName(row, 1, "contactsTablePhoneCell");

		// if we want alternate colored rows:
		if ((row % 2) == 0) {
			getRowFormatter().addStyleName(row, "contactsTableCellEven");
		} else {
			getRowFormatter().addStyleName(row, "contactsTableCellOdd");
		}

		Button botaoLista = new Button("Menu");
		botaoLista.setStyleName("deleteContactButton");
		setWidget(row, 3, botaoLista);

		botaoLista.addClickHandler(new ClickHandler() { 

			@Override
			public void onClick(ClickEvent event) {

				final int selectedRow = getCellForEvent(event).getRowIndex();
				final String nomeRestaurante = getText(selectedRow, 0);
				final String moradaRestaurante = getText(selectedRow, 1);
				final String classficacaoRestaurante = getText(selectedRow, 2);
				final RestauranteSimpleDto r = new RestauranteSimpleDto(nomeRestaurante, moradaRestaurante, Integer.parseInt(classficacaoRestaurante));
				clearRestaurantList();  
				rootPage.showMenuPage(loggedPerson, r);
			}
			
		});
	}
}