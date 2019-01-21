// Messy, needs a tidy.


package PaymentSimpleModel.Model;

import Staff.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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

public class ConnectionToDatabase {

    Connection conn; // Variable of type Connection to hold the connection details
    // this is set by the call to the connectDB() method

    PreparedStatement pstmt; // Variable to hold your search String
    ResultSet rs; // Variable to hold the results retrieved from the database
    String dbName = "CarSales"; // Variable to hold name of Database Table
    
    //Variables to hold the Sales information
    String carDescription;
    double totalPaid;
    String paymentPlan;
    String paymentMethod;
    FraStafLogInView staffLogIn;
    FraManagerAuthorisation managerAuth;
    public String username;
    public String password;
    public String role;
    public FraAddStaff addStaff;

    // Java Constructor -Called when adding a new record, so that the sales information can be passed- 
    // Java Constructor - if the database isn't there then we'll get an error - 
    // the SQLException will handle this for us 
    public ConnectionToDatabase(String _carDescription, double _totalPaid, String _paymentPlan, String _paymentMethod) throws SQLException
    {
        //Convert Passed variables
        carDescription = _carDescription;
        totalPaid = _totalPaid;
        paymentPlan = _paymentPlan;
        paymentMethod = _paymentMethod;
    }
    
    public ConnectionToDatabase(){
        
    }
    public ConnectionToDatabase(FraStafLogInView _staffLogIn){
        this.staffLogIn = _staffLogIn;
    }
    //public ConnectionToDatabase(FraManagerAuthorisation _staffLogIn){
    //    this.managerAuth = _staffLogIn;
    //}

    /**
     *
     * @return
     */
    public Connection connectDB() {
        
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
           return conn;

        } catch (SQLException e) // if the connection fails, show the error messages  
        {
            System.err.println("Grr - Cannot connect the database!" + e);
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionToDatabasetest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void closeConnection() throws SQLException{
        conn.close();
        System.out.println("Connection closed.");
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    //TODO I need to create an additional sql select for checking how many managers there are,
    //and then have the second select for deleting the manager.
    ///////////////////////////////////////////////////
    
    public void deleteUser(String _username, FraDeleteUser _deleteUserView) throws SQLException{
        connectDB();
        
        username = _username;
        
        //Check if it is a valid username///////////////////////////////////////////////////////////////////
        String checkUsersql = "SELECT * FROM Users WHERE Username = ?";
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(checkUsersql);
            
            pstmt.setString(1, username);
            
            // execute the SQL stetement
            rs = pstmt.executeQuery();
            if (rs.next()){
                 System.out.println("User found");
                }
            else{
                JOptionPane.showMessageDialog(null, "User not found");
                return;
            }
            
            //Check if it is a manager///////////////////////////////////////////////////////////////////
            String checkManagerSql = "SELECT * FROM Users WHERE Username = ?";
            //String checkManagerSql = "SELECT * FROM Users WHERE Role = 'Manager'";
            try {
                conn.setAutoCommit(false);
                pstmt = conn.prepareStatement(checkManagerSql);

                // add the values to the INSERT command
                // i.e. replace the question marks
                pstmt.setString(1, username);


                // execute the insert SQL stetement
                rs = pstmt.executeQuery();

                ///////////////// Check that there will be a manager left
                if(rs.next()){
                    role = rs.getString("Role");
                }
                if(role.equals("Manager")){
                    String checkNumManagers = "SELECT * FROM Users WHERE Role = 'Manager'";
                    try{
                        conn.setAutoCommit(false);
                        pstmt = conn.prepareStatement(checkNumManagers);
                        rs = pstmt.executeQuery();
                    }
                    catch(Exception e) {
                        
                    }
                    rs.last();
                   int count = rs.getRow();
                    rs.beforeFirst();
                   if (count > 1){
                       //Code for deleting manager if there is more than one manager
                            try {
                                conn.setAutoCommit(false);

                                // Create the DELETE command
                                String sql = "DELETE FROM Users WHERE Username = ? ";
                                PreparedStatement prst1 = conn.prepareStatement(sql);

                                // use the prodID variable declared at the top of this method
                                prst1.setString(1, username);

                                int val = prst1.executeUpdate();
                                System.out.println("The record " + username + " has been deleted successfully.");
                                JOptionPane.showMessageDialog(null, "The record " + username + " has been deleted successfully.");
                                conn.commit();
                                _deleteUserView.dispose();
                            } 
                            catch (SQLException e) {
                                 System.err.println("Can't delete record" + e);
                            }
                       
                   }
                   else {
                       JOptionPane.showMessageDialog(null, "There must always be a manager user.");
                        }
                }
                else {
                    //Code for deleting non manager user
                    try {
                                conn.setAutoCommit(false);

                                // Create the DELETE command
                                String sql = "DELETE FROM Users WHERE Username = ? ";
                                PreparedStatement prst = conn.prepareStatement(sql);

                                // use the prodID variable declared at the top of this method
                                prst.setString(1, username);

                                int val = prst.executeUpdate();
                                System.out.println("The record " + username + " has been deleted successfully.");
                                JOptionPane.showMessageDialog(null, "The record " + username + " has been deleted successfully.");
                                conn.commit();
                                _deleteUserView.dispose();
                            } 
                            catch (SQLException e) {
                                 System.err.println("Can't delete record" + e);
                            }
                }
            }
            catch (Exception e){
                System.out.println("Error : " + e);
            }
        }
        catch (Exception e){
            System.out.println("User not found");            
        }
    }
    
    
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void addNewRecord() throws SQLException{
        
        connectDB();
         
        ////////////////////////////
        //SQL for adding the record
        ////////////////////////////

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

        } 
        ////////////////////////////
        ////////////////////////////
        closeConnection();
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void getAuthorisation(String _username, String _password, String _role, FraAddStaff _addStaff) throws SQLException{
       this.username = _username;
       this.password = _password;
       this.role = _role;
       this.addStaff = _addStaff;
       System.out.println(""+username+" " +password+ " "+role);
       FraManagerAuthorisation mangerAuthorisationView = new FraManagerAuthorisation(this);
       mangerAuthorisationView.setVisible(true);
       
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void addNewStaff() throws SQLException{
        
        connectDB();
         
        ////////////////////////////
        //SQL for adding the record
        ////////////////////////////

        PreparedStatement pstmt = null;

        // Notice how we split the query into an INSERT command
        // and the values - the values are inserted in place of the question
        // marks
        String _insertTableSQL = "INSERT INTO Users (Username, Password, Role) VALUES (?,?,?)";

        // try/catch - in cse of an error 
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(_insertTableSQL);

            // add the values to the INSERT command
            // i.e. replace the question marks
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
           

            // execute the insert SQL stetement
            pstmt.executeUpdate();

            System.out.println("Record inserted into User table!");
            JOptionPane.showMessageDialog(addStaff,"New User Added");
            addStaff.dispose();
            conn.commit(); // actually change the underlying database
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());

        } 
        ////////////////////////////
        ////////////////////////////
        closeConnection();
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void managerauthorisation(String _username, String _password, FraManagerAuthorisation _managerAuthorisation){
        managerAuth = _managerAuthorisation;
        connectDB();
        String username = _username;
        String password = _password;
        PreparedStatement pstmt = null;
        
        String loginSql = "SELECT * FROM Users WHERE Username = ? and Password = ? and Role = 'Manager'";
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(loginSql);
            
            // add the values to the INSERT command
            // i.e. replace the question marks
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // execute the insert SQL stetement
            rs = pstmt.executeQuery();
            if (rs.next()){
                System.out.println("Authorisation complete");
                addNewStaff();
                managerAuth.dispose();
            }
            else {
               JOptionPane.showMessageDialog(null, "Invalid Username and/or Password", "Access Denied", JOptionPane.ERROR_MESSAGE);
               managerAuth.setScreen();
            }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    
    public void logIn(String _username, String _password){
        connectDB();
        String username = _username;
        String password = _password;
        PreparedStatement pstmt = null;
        
        String loginSql = "SELECT * FROM Users WHERE Username = ? and Password = ?";
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(loginSql);
            
            // add the values to the INSERT command
            // i.e. replace the question marks
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // execute the insert SQL stetement
            rs = pstmt.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null, "Welcome User!");
                System.out.println("Log in successful");
                FraStaffAccess staffAccess = new FraStaffAccess();
                staffAccess.setVisible(true);
                staffLogIn.dispose();
            }
            else {
               JOptionPane.showMessageDialog(null, "Invalid Username and/or Password", "Access Denied", JOptionPane.ERROR_MESSAGE);
               staffLogIn.setScreen();
            }
            
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionToDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    
    public void salesTable() {
        
        
        String simpleQuery = "select * from CarSales";
        System.out.println("");
        System.out.println("Running Simple Query");
        System.out.println("Getting Result... ");

        try {
            conn.setAutoCommit(false);

            // the variable simpleQuery is declared at the top of this method
            pstmt = conn.prepareStatement(simpleQuery);
            pstmt.executeQuery();
            // get the results
            ResultSet rs = pstmt.getResultSet();

            System.out.println("" + conn.getCatalog());

            while (rs.next()) {
               //catalog = "Database Table is " + conn.getCatalog();
                // get the Product No and put it into the variable prodNo
                String saleID = rs.getString("Sale_ID");
                // get the Product Description and put it into the variable prodDesc
                String carDescription = rs.getString("Car_Description");
                // get the Product Price and put it into the variable prodPrice
                double totalPaid = rs.getDouble("Total_Paid");
                // get the Product Description and put it into the variable prodDesc
                String paymentPlan = rs.getString("Payment_Plan");
                // get the Product Description and put it into the variable prodDesc
                String paymentMethod = rs.getString("Payment_Method");

                System.out.println(
                        "\t" + "Sale Id: " + saleID
                        + "\t" + "Car Description: " + carDescription
                        + "\t" + "Total Paid: " + totalPaid
                        + "\t" + "Payment Plan: " + paymentPlan
                        + "\t" + "Payment Method: " + paymentMethod);
            }
        } catch (SQLException e) {
            System.out.println("" + e);
        }
      
    }
}
