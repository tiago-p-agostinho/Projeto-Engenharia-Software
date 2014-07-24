package pt.ist.rest.presentation.client;


import pt.ist.rest.exception.ArgumentosInvalidosException;
import pt.ist.rest.exception.ClientNotFoundException;
import pt.ist.rest.presentation.client.RestGWT;
import pt.ist.rest.presentation.client.RestServletAsync;
import pt.ist.rest.service.dto.ClienteDto;
import pt.ist.rest.service.dto.UtilizadorDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;


public class LoginPage extends Composite {
	private Button btnLogin;
	private TextBox textBoxUser;
	private TextBox textBoxPass;

   public LoginPage(final RestGWT rootPage, final RestServletAsync rpcService) {		
	   FlexTable flexTable = new FlexTable();
    
	    Label lblUser = new Label("User:");
	    lblUser.setStyleName("label");
	    flexTable.setWidget(0, 0, lblUser);
	    lblUser.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    
	    Label lblPass = new Label("Pass:");
	    lblPass.setStyleName("label");
	    flexTable.setWidget(2, 0, lblPass);
	    lblPass.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	    
	    textBoxPass = new PasswordTextBox();
	    flexTable.setWidget(3, 0, textBoxPass);
	    
	    textBoxUser = new TextBox();
	    flexTable.setWidget(1, 0, textBoxUser);
	    
	    textBoxPass.addKeyUpHandler(new KeyUpHandler() {
		public void onKeyUp(KeyUpEvent event) {
		  if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		    doLogin(rootPage, rpcService);
		  }
		}});
	    
	    textBoxUser.addKeyUpHandler(new KeyUpHandler() {
	    	public void onKeyUp(KeyUpEvent event) {
	    	  if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    	    doLogin(rootPage, rpcService);
	    	  }
	    }});
	    
	    btnLogin = new Button("login");
	    flexTable.setWidget(3, 2, btnLogin);
	    
	    btnLogin.addClickHandler(new ClickHandler() {
		public void onClick(ClickEvent event) {
			
		  doLogin(rootPage, rpcService);
		
		}
	      });
	    
	    initWidget(flexTable);
  }
   
   private final void doLogin(final RestGWT rootPage, final RestServletAsync rpcService) {
	    // if any of the fields is empty, show warning:
	    if (this.textBoxUser.getText().equals("") || this.textBoxPass.getText().equals("")){
	      rootPage.showErrorMessage("Please fill the username or the password!");
	      return;
	    }
	    
	    final ClienteDto dto = new ClienteDto(this.textBoxUser.getText(),this.textBoxPass.getText());
	   
	   
	    rpcService.login(dto, new AsyncCallback<Void>() {
	    
		public void onSuccess(Void response) {
		  rootPage.showRestaurantePage(dto);
		}
		
		public void onFailure(Throwable caught) {
		  GWT.log("presentationserver.client.rest::onModuleLoad()::rpcService.login");
		  GWT.log("-- Throwable: '" + caught.getClass().getName() + " Message '" + caught.getMessage());
		  if (caught instanceof ClientNotFoundException) {
		     rootPage.showErrorMessage("Client does not exist.");
		  }
		  if (caught instanceof ArgumentosInvalidosException) {
			  rootPage.showErrorMessage("Password incorrecta");
		  }
		}
	 });
	  
	 
   }
}
	    
