package pt.ist.rest;


import pt.ist.rest.domain.Rest;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;

public class DatabaseBootstrap {
  private static boolean notInitialized = true;

  public final static String DBUSER = "rest";
	public final static String DBPASS = "r3st";
	public final static String DBURL = "//localhost:3306/restdb";
	public final static String DMLPATH = "src/main/dml/rest.dml";
	
  public synchronized static void init() {
    if (notInitialized) {
       	FenixFramework.initialize(new Config() {{
    		    dbAlias = DBURL;
            dbUsername = DBUSER;
            dbPassword = DBPASS;
		        domainModelPath = DMLPATH;
		        rootClass=Rest.class;
          }});
       }

    notInitialized = false;
  }

  public static void setup() {
    try {
      pt.ist.rest.restSetup.populateDomain();
    }catch (Exception e){
        System.out.println(e.getMessage());
   	   }
  }
}
