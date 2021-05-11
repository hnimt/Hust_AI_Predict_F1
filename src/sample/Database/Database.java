package sample.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/covid19";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String MAX_POOL = "250";

    //make "single object"
    private static Database instance = new Database();
    public static Database getInstance(){
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    private Connection connection;
    public Connection connect(){
        if (connection == null){
            try {
                //add params before connection
                Properties properties = new Properties();
                properties.setProperty("user", USERNAME);
                properties.setProperty("password", PASSWORD);
                // Max request to db
                properties.setProperty("MaxPooledStatements", MAX_POOL);
                // Check whether DriverManager exists ?
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, properties);
            } catch (ClassNotFoundException | SQLException exception){
                System.err.println("Cannot connect to DB, error: " + exception.toString());
            }
        }
        return connection;
    }

    // disconnect db when cannot connect
    public void disconnect() {
        if (connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
