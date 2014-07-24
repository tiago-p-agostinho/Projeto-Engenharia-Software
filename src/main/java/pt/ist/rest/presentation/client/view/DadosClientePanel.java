package pt.ist.rest.presentation.client.view;

import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.exception.DishNotFoundException;
import pt.ist.rest.exception.RestaurantNotFoundException;
import pt.ist.rest.presentation.client.RestGWT;
import pt.ist.rest.presentation.client.RestServletAsync;
import pt.ist.rest.presentation.client.view.ListaTabuleiroPanel.CustomLabel;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.ItemDto;
import pt.ist.rest.service.dto.PratoSimpleDto;
import pt.ist.rest.service.dto.RestauranteSimpleDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

public class DadosClientePanel extends FlexTable{


	private final RestServletAsync rpcService;
	private ClienteDto loggedPerson = null;
	private RestGWT rootPage;

	public DadosClientePanel(RestGWT rootPage, RestServletAsync rpcService) {
			  
			   
		// set RPC service
		this.rpcService = rpcService;
		// format table main features:
		addStyleName("contactsTable");
		// add header row:
   		setText(0, 0,"Nome: ");
    	setText(1,0,"Username: ");
	    setText(2,0,"Morada: ");
	    setText(3,0,"Email: ");
	    
	    this.rootPage = rootPage;
	}
		  
	public final void setLoggedPerson(ClienteDto dto) {
		this.loggedPerson = dto;
	}

	public void add(ClienteDto cliente) {
		// GWT.log("presentation.client.view.AlterarQuantidadePanel::add(" + item + ")");
	    final String nomeCliente = cliente.getNome();
	    final String userCliente = cliente.getUser();
	    final String moradaCliente = cliente.getMorada();
	    final String emailCliente = cliente.getMail();
	    final int  row = getRowCount();

	    setText(0, 1, nomeCliente);
	    setText(1, 1, userCliente);
	    setText(2, 1, moradaCliente);
	    setText(3, 1, emailCliente);
		    

	    // if we want alternate colored rows:
	    if ((row % 2) == 0) {
	    	getRowFormatter().addStyleName(row, "contactsTableCellEven");
	    } else {
	    	getRowFormatter().addStyleName(row, "contactsTableCellOdd");
	    }
    }
		  

	public void clearList(){
		GWT.log("presentation.client.view.DadosClientePanel::clearList()");
		int rowCount = getRowCount();

		for (int i = rowCount - 1; i > 0; i--)
			removeRow(i);
	}	

}
