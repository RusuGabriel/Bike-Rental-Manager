package manager;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    private static Database instance;
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;database=BikeManagement;integratedSecurity=true";
    private Connection connection;
    static ResultSet resultSet = null;
    private static ArrayList<String> attributes = new ArrayList<String>();

    private Database() {
        try {
            connection = DriverManager.getConnection(connectionString);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("Error at connecting to the database!");
            System.out.println(e.getMessage());
        }
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public boolean execute(String command) {
        return true;
    }

    private static int getNumberOfColumns(ResultSet set) throws SQLException {
        ResultSetMetaData rsmd = set.getMetaData();
        return rsmd.getColumnCount();
    }

    public static void loadFrom(String tableName, UserRepository data) {
        if (!data.getInstance().getData().isEmpty())
            return;
        ResultSet resultSet = null;
        int columnsNumber = 0;

        try {
            Statement statement = instance.connection.createStatement();
            String selectSql = "SELECT * FROM [" + tableName + "];";
            resultSet = statement.executeQuery(selectSql);
            columnsNumber = getNumberOfColumns(resultSet);

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.getInstance().getData().add(new User(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadFrom(String tableName, BikeRepository data) {
        //TODO: Modify this method to join the BikeBrand BikeCategory and BikeTable into a single unit
        ResultSet resultSet = null;
        int columnsNumber = 0;

        try {
            Statement statement = instance.connection.createStatement();
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * FROM [" + tableName + "];";
            resultSet = statement.executeQuery(selectSql);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            columnsNumber = rsmd.getColumnCount();

            // Print results from select statement
            while (resultSet.next()) {
                System.out.println("Number of columns: " + columnsNumber);
                for (int i = 3; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.getInstance().getData().add(new Bike(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveTo(String tableName, Bike newData) {
        //TODO:Implement the save opperation for bikes

    }

    public static void saveTo(String tableName, User newData) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("INSERT INTO [" + tableName + "] VALUES(" + newData.toString() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String tableName, String column, String value, User user) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("UPDATE [" + tableName + "] SET " + column + "=" + value + " WHERE CNP =" + user.getCNP() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFrom(String tableName, User registredUser) {
        //TODO: Implement the delete operation for the User
    }

}

