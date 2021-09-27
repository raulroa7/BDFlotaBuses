package main;

import integration.database.mysql.MySqlOperations;
import org.apache.log4j.PropertyConfigurator;
import java.sql.SQLException;
import static util.enums.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static util.enums.SystemProperties.USER_DIR;

public class Main {

    private static final String SERVER = "sofka-training.cpxphmd1h1ok.us-east-1.rds.amazonaws.com";
    private static final String DATA_BASE_NAME = "raulRoa_flotaBuses_10092021";
    private static final String USER = "sofka_training";
    private static final String PASSWORD = "BZenX643bQHw";

    private static final String SELECT_ALL_FROM_CONCESIONARIO = String.format("select * from Concesionario", DATA_BASE_NAME);
    private static final String SELECT_ALL_FROM_BUS= String.format("select * from Bus", DATA_BASE_NAME);
    private static final String CALL_SP_MOSTRAR_CONCESIONARIOPADRON= String.format("call mostrar_concesioanrioPadron()");

    private static final MySqlOperations mySqlOperations = new MySqlOperations();

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());

        login();
        selectAllFromCiudad();
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        selectAllFromDirector();
        System.out.println("*********************************************");
        System.out.println("*********************************************");
        callSpListarPeliculas();
        logout();

    }

    private static void login(){
        mySqlOperations.setServer(SERVER);
        mySqlOperations.setDataBaseName(DATA_BASE_NAME);
        mySqlOperations.setUser(USER);
        mySqlOperations.setPassword(PASSWORD);
    }

    private static void selectAllFromCiudad() throws SQLException {
        mySqlOperations.setSqlStatement(SELECT_ALL_FROM_CONCESIONARIO);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void selectAllFromDirector() throws SQLException {
        mySqlOperations.setSqlStatement(SELECT_ALL_FROM_BUS);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void callSpListarPeliculas() throws SQLException {
        mySqlOperations.setSqlStatement(CALL_SP_MOSTRAR_CONCESIONARIOPADRON);
        mySqlOperations.executeSqlStatement();
        mySqlOperations.printResultSet();
    }

    private static void logout(){
        mySqlOperations.close();
    }

}

