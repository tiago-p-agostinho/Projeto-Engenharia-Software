package pt.ist.rest.presentation.client.view;

import pt.ist.rest.presentation.client.RestGWT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ChangeHandler;




public class MenuOptionsPanel extends DecoratorPanel {
  
  private final VerticalPanel verticalPanel;
  private final HorizontalPanel pagamentoPanel;

  private final CheckBox checkNif;
  private final TextBox textBoxCheque;
  private final TextBox textBoxPrato;
  private final Button listaRestauranteButton;
  private final Button mostrarTabuleiroButton;
  private final Button efectuarPagamentoButton;
  private final Button procuraPratoButton;
  private final ListBox opcoesProcura;
  private final Button adicionarChequesButton;
  private final Button dadosClienteButton;
  
  public MenuOptionsPanel(RestGWT rootPanel) {
    GWT.log("presentation.client.view.CreateContactPanel::constructor()");

    this.verticalPanel = new VerticalPanel();
    this.pagamentoPanel = new HorizontalPanel();

    Label lblUser = new Label("Introduza o numero de cheque(ex: 10 5 30):");
    Label lblPrato = new Label("Introduza o tipo ou o nome do prato:");
    lblUser.setStyleName("label");
    lblPrato.setStyleName("label");
    this.textBoxCheque = new TextBox();
    this.checkNif = new CheckBox("c/NIF");
    this.listaRestauranteButton = new Button("Lista Restaurantes");
    this.mostrarTabuleiroButton = new Button("Mostrar Tabuleiro");
    this.adicionarChequesButton = new Button ("Adicionar Cheques");
    this.efectuarPagamentoButton = new Button ("Pagar");
    this.dadosClienteButton = new Button("Dados do Cliente");
    //PESQUISA
    this.textBoxPrato = new TextBox();
    this.opcoesProcura = new ListBox();
    this.procuraPratoButton = new Button("Procura Prato");
    initOpcoesProcura(); 



    this.verticalPanel.add(this.listaRestauranteButton);
    this.verticalPanel.add(this.mostrarTabuleiroButton);

    this.verticalPanel.add(lblPrato);
    this.verticalPanel.add(opcoesProcura);
    this.verticalPanel.add(this.textBoxPrato);
    this.verticalPanel.add(this.procuraPratoButton);

    this.verticalPanel.add(lblUser);
    this.verticalPanel.add(this.textBoxCheque);
    this.pagamentoPanel.add(this.adicionarChequesButton);
    this.pagamentoPanel.add(this.efectuarPagamentoButton);
    this.pagamentoPanel.add(this.checkNif);
    this.verticalPanel.add(pagamentoPanel);
    this.verticalPanel.add(this.dadosClienteButton);
     
    this.add(this.verticalPanel);
 
    
  }

  public void setClickHandlerRestaurantes(ClickHandler handler) {
	    this.listaRestauranteButton.addClickHandler(handler);
  }
  public void setClickHandlerTabuleiro(ClickHandler handler){
	  this.mostrarTabuleiroButton.addClickHandler(handler);
  }
    
  public void setClickHandlerEfectuarPagamento(ClickHandler handler){
	  this.efectuarPagamentoButton.addClickHandler(handler);
  }

  public void setClickHandlerAdicionarCheques(ClickHandler handler){
    this.adicionarChequesButton.addClickHandler(handler);
  }
  
  public void setClickHandlerEfectuarPesquisa(ClickHandler handler){
	  this.procuraPratoButton.addClickHandler(handler);
  }
  public void setChangedTipoPesquisa(ChangeHandler handler){
    this.opcoesProcura.addChangeHandler(handler);
  }

  public void setClickHandlerDados(ClickHandler handler){
    this.dadosClienteButton.addClickHandler(handler);
  }


  public void initOpcoesProcura(){
    this.opcoesProcura.addItem("carne");
    this.opcoesProcura.addItem("peixe");
    this.opcoesProcura.addItem("vegetariano");
    this.opcoesProcura.setVisibleItemCount(1);
  }
  
  public void clearChequeBox(){
    this.textBoxCheque.setText("");
  }

  public TextBox getChequeBox(){
	  return this.textBoxCheque;
  }
  
  public TextBox getPratoBox(){
	  return this.textBoxPrato;
  }
  public CheckBox getCheckNif(){
    return this.checkNif;
  }
  public ListBox getListProcura(){
    return this.opcoesProcura;
  }
}