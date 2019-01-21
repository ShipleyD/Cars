package PaymentSimpleModel.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * You will need to create a database in mySQL called ProductCatalog
 * with one table named ProductCatalog
 * with the fields:
 * ProductNo - a VARCHAR of 10 (set as the Primary Key)
 * ProductDescription - a VARCHAR of 50
 * ProductPrice - a DECIMAL
 * Now add 2 or three records using phpmyadmin
 * 
 * To connect the java project and the mySQL Database
 * click on the services tab
 * click on Drivers
 * right click on the MySQL (Connector/J Driver)
 * select connect using
 * enter your mySQL username and password
 * and test the connection
 * click finish
 * 
 * Now right click on the jdbc:mysql connection
 * and choose View Data
 * 
 * Try some SQL commands from the editor
 * 
 */

/*
 If it isn't there already you will need to add the mySQLJDBC Driver to the
 * library folder when you configure the project
 */

public class ConnectionToDatabasetest {

    Connection conn; // Variable of type Connection to hold the connection details
    // this is set by the call to the addNewRecord() method

    PreparedStatement pstmt; // Variable to hold your search String
    ResultSet rs; // Variable to hold the results retrieved from the database
    String dbName = "CarSales"; // Variable to hold name of Database Table
    
    //Variables to hold the Sales information
    String carDescription;
    double totalPaid;
    String paymentPlan;
    String paymentMethod;

    // Java Constructor - if the database isn't there then we'll get an error - 
    // the SQLException will handle this for us 
    public ConnectionToDatabasetest(String _carDescription, double _totalPaid, String _paymentPlan, String _paymentMethod) throws SQLException
    {
        //Convert Passed variables
        carDescription = _carDescription;
        totalPaid = _totalPaid;
        paymentPlan = _paymentPlan;
        paymentMethod = _paymentMethod;
    }

    /**
     *
     * @return
     */
    public Connection addNewRecord()
    {
        // create a variable to hold the connection and initialise to null 
        conn = null;

        // connection details from the connection window when you set this up
        String url = "jdbc:mysql://localhost:3306/CarSales";
        String mySQLUsername = "root"; // yours may be different
        String mySQLPassword = "";// yours may be different

        System.out.println("");
        System.out.println("Trying to connect to Database");

        // try to connect to the database with the values in the url, username,
        // and password variables 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, mySQLUsername, mySQLPassword);
            // set the variable at the top with the connection details
            conn = connection;
            System.out.println("Database connected!");
            return conn; // so that all the rest of the methods can use it (the connection)

        } catch (SQLException e) // if the connection fails, show the error messages  
        {
            System.err.println("Grr - Cannot connect the database!" + e);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToDatabasetest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
//        try {
//            // return connection details to the variable declared at the top
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(ConnectionToDatabasetest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
    public void closeConnection() throws SQLException{
        conn.close();
        System.out.println("Connection closed. woop woop");
    }
    
    public void addRecord(){
            //conn = addNewRecord();
            PreparedStatement pstmt = null;

            // Notice how we split the query into an INSERT command
            // and the values - the values are inserted in place of the question
            // marks
            String insertTableSQL = "INSERT INTO carsales"
                    + "(Car_Description, Total_Paid, Payment_Plan, Payment_Method) VALUES"
                    + "(?,?,?,?)";

            // try/catch - in cse of an error 
            try {
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(insertTableSQL);

                // add the values to the INSERT command
                // i.e. replace the question marks
                pstmt.setString(1, carDescription);
                pstmt.setDouble(2, totalPaid);
                pstmt.setString(3, paymentPlan);
                pstmt.setString(4, paymentMethod);

                // execute the insert SQL stetement
                pstmt.executeUpdate();

                System.out.println("Record inserted into ProductCatalog table!");
                conn.commit(); // actually change the underlying database
            } catch (SQLException e) {

                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());

            } finally {
                if (conn != null) {
                    //conn.close();
                }
            }
        
    }
    
    /**
     *
     */
    public Connection logIn(){
        
        // create a variable to hold the connection and initialise to null 
        conn = null;

        // connection details from the connection window when you set this up
        String url = "jdbc:mysql://localhost:3306/CarSales";
        String mySQLUsername = "root"; // yours may be different
        String mySQLPassword = "";// yours may be different

        System.out.println("");
        System.out.println("Trying to connect to Database");

        // try to connect to the database with the values in the url, username,
        // and password variables 
        try (Connection connection = DriverManager.getConnection(url, mySQLUsername, mySQLPassword)) {
            // set the variable at the top with the connection details
            conn = connection;
            System.out.println("Database connected!");
            
            ////////////////////////////
            //SQL for Checking username and password
            ////////////////////////////
            
            PreparedStatement pstmt = null;

            String insertTableSQL = "INSERT INTO carsales" + "(Car_Description, Total_Paid, Payment_Plan, Payment_Method) VALUES" + "(?,?,?,?)";

            // try/catch - in cse of an error 
            try {
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(insertTableSQL);

                // add the values to the INSERT command
                // i.e. replace the question marks
                pstmt.setString(1, carDescription);
                pstmt.setDouble(2, totalPaid);
                pstmt.setString(3, paymentPlan);
                pstmt.setString(4, paymentMethod);

                // execute the insert SQL stetement
                pstmt.executeUpdate();

                System.out.println("Record inserted into ProductCatalog table!");
                conn.commit(); // actually change the underlying database
            } catch (SQLException e) {

                System.out.println(e.getMessage());
                System.out.println(e.getErrorCode());

            } finally {
                if (conn != null) {
                    //conn.close();
                }
            }
            ////////////////////////////
            ////////////////////////////

        } catch (SQLException e) // if the connection fails, show the error messages  
        {
            System.err.println("Grr - Cannot connect the database!" + e);
        }
        try {
            // return connection details to the variable declared at the top
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDatabasetest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn; // so that all the rest of the methods can use it (the connection)
    }
}
