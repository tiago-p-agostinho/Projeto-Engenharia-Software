package pt.ist.rest;



import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import jvstm.Atomic;


import pt.ist.rest.domain.*;

public class restSetup {

    public final static String DBUSER = "rest";
	public final static String DBPASS = "r3st";
	public final static String DBURL = "//localhost:3306/restdb";
	public final static String DMLPATH = "src/main/dml/rest.dml";

    public static void main(String[] args) {
				FenixFramework.initialize(new Config() {{
                dbAlias = DBURL;
                dbUsername = DBUSER;
                dbPassword = DBPASS;
    		    domainModelPath = DMLPATH;
    		    rootClass=Rest.class;
    	}});

    	populateDomain();
    }
    
    @Atomic
    public static void populateDomain() {
		try{
           Rest rest = FenixFramework.getRoot();




           Gestor mng = new Gestor("Passos Lebre","mng","nm8");
           Gestor pp = new Gestor("Paulo Portao","pp","pp");

           Cliente zeze = new Cliente("Ze Ninguem","Lisboa,Portugal","zeze","ze.ninguem@ist.utl.pt","z3z3",new Integer(2222));
           Cliente maria = new Cliente("Maria Silva","Porto,Portugal","mariazinha","maria.silva@ist.utl.pt","****",new Integer(1111));
           Cliente alice = new Cliente("Alice Alves","Aveiro,Portugal","alice","alice.alves@sonet.pt","aaa",new Integer(1001));
           Cliente bruno = new Cliente("Bruno Bento","Beja,Portugal","bruno","bruno.bento@sonet.pt","bbb",new Integer(3333));
           Cliente carlos = new Cliente("Carlos Calado","Coimbra,Portugal","carlos","carlos.calado@sonet.pt","ccc",new Integer(4444));


           Prato bacalhauF = new Prato("Bacalhau com batatas",1,300,rest.incrementaIDPrato());
           bacalhauF.addAlimento(new Alimento(Prato.PEIXE));
           bacalhauF.addAlimento(new Alimento(Prato.VEGETARIANO));

           Prato bacalhauC = new Prato("Bacalhau com batatas",2 ,700,rest.incrementaIDPrato());
           bacalhauC.addAlimento(new Alimento(Prato.PEIXE));
           bacalhauC.addAlimento(new Alimento(Prato.VEGETARIANO));

           Prato bitoque = new Prato("Bitoque",3 ,500,rest.incrementaIDPrato());
           bitoque.addAlimento(new Alimento(Prato.CARNE));
           bitoque.addAlimento(new Alimento(Prato.VEGETARIANO));
           bitoque.addAlimento(new Alimento(Prato.VEGETARIANO));

           Prato canja = new Prato("Canja de Galinha",4 ,150,rest.incrementaIDPrato());
		       canja.addAlimento(new Alimento(Prato.VEGETARIANO));
           canja.addAlimento(new Alimento(Prato.CARNE));


           /*PRATOS EXTRA para teste de funcionalidade de procura por tipo de alimento*/
           Prato omeleteV = new Prato("Omelete vegetariana",5, 100,rest.incrementaIDPrato());
           omeleteV.addAlimento(new Alimento( Prato.VEGETARIANO));
           omeleteV.addAlimento(new Alimento(Prato.VEGETARIANO));

           Prato pratoMisto = new Prato("Prato misto", 6,900,rest.incrementaIDPrato());
           pratoMisto.addAlimento(new Alimento(Prato.CARNE));
           pratoMisto.addAlimento(new Alimento(Prato.PEIXE));



           Restaurante cheia = new Restaurante("Barriga Cheia","Lisboa,Portugal");
           Restaurante feliz = new Restaurante("Barriga Feliz","Lisboa,Portugal");

           rest.addRestaurante(cheia);
           rest.addRestaurante(feliz);

           rest.addCliente(zeze);
           rest.addCliente(maria);
           rest.addCliente(alice);
           rest.addCliente(bruno);
           rest.addCliente(carlos);

           cheia.addGestor(mng);
           feliz.addGestor(pp);

           mng.getRestaurante().adicionaPrato(bacalhauC);
           mng.getRestaurante().adicionaPrato(omeleteV);
           pp.getRestaurante().adicionaPrato(bacalhauF);
           pp.getRestaurante().adicionaPrato(bitoque);
           pp.getRestaurante().adicionaPrato(canja);
           pp.getRestaurante().adicionaPrato(pratoMisto);

        }catch (Exception e){
         System.out.println(e.getMessage());
    	   }
    }
	
}


