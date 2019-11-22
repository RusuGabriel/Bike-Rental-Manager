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
        if (!data.getInstance().getData().isEmpty())
            return;
        int columnsNumber = 0;

        try {
            Statement statement = instance.connection.createStatement();
            String selectSql = "select b.Bike_ID 'bike_ID',b.Color 'Color' , b.Speeds 'Speed'," +
                    "bB.Brand_ID 'brand_ID' ,bB.BrandName 'Brand', bC.Category_ID 'category_Id',bC.CategoryName 'Category'" +
                    "from BikeBrands bB, BikeCategory bC, Bike b\n" +
                    "where b.Brand_ID = bB.Brand_ID and b.Category_ID = bC.Category_ID";
            resultSet = statement.executeQuery(selectSql);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            columnsNumber = rsmd.getColumnCount();

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++)
                    attributes.add(resultSet.getString(i));
                data.getInstance().getData().add(new Bike(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void loadFrom(String tableName, RentalInvoiceRepository data) {
        if (!data.getInstance().getData().isEmpty())
            return;
        int columnsNumber = 0;

        try {
            Statement statement = instance.connection.createStatement();
            String selectSql = "select ri.Rental_ID, u.User_ID ,u.First_Name +' '+u.Second_Name as \"Full Name\", b.Brand_ID,bb.BrandName,ri.StartTime,ri.EndTime, p.PricePerHour*DATEDIFF(hh,ri.StartTime, ri.EndTime) as \"To Pay\" from RentalInovice ri join [User] u on u.User_ID = ri.User_ID join\n" +
                    "            Bike b on ri.Bike_ID= b.Bike_ID join dbo.BikeBrands bb on bb.Brand_ID = b.Brand_ID join Price p on b.Price_ID = p.Price_ID;";
            resultSet = statement.executeQuery(selectSql);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            columnsNumber = rsmd.getColumnCount();

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    attributes.add(resultSet.getString(i));
                }
                data.getInstance().getData().add(new RentalInvoice(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveTo(String tableName, Bike newData) {
        try {
            int pK = 0;
            Statement statement = instance.connection.createStatement();
            if (!BikeRepository.getInstance().contains(newData.getBikeCategory())) {

                statement.executeUpdate("INSERT INTO [" + tableName + "Category" + "] VALUES(" + newData.getBikeCategory().toString() + ")");
                resultSet = statement.executeQuery("SELECT IDENT_CURRENT ('BikeCategory') AS Current_Identity;");
                if (resultSet.next())
                    pK = resultSet.getInt(1);
                newData.getBikeCategory().setPrimaryKey(pK);
            } else {
                resultSet = statement.executeQuery("SELECT Category_ID FROM [BikeCategory] WHERE CategoryName = " + newData.getBikeCategory().toString() + ";");
                if (resultSet.next())
                    pK = resultSet.getInt(1);
                newData.getBikeCategory().setPrimaryKey(pK);
            }
            if (!BikeRepository.getInstance().contains(newData.getBikeBrand())) {
                statement.executeUpdate("INSERT INTO [" + tableName + "Brands" + "] VALUES(" + newData.getBikeBrand().toString() + ")");
                resultSet = statement.executeQuery("SELECT IDENT_CURRENT ('BikeBrands') AS Current_Identity;");
                if (resultSet.next())
                    pK = resultSet.getInt(1);
                newData.getBikeBrand().setPrimaryKey(pK);
            } else {
                resultSet = statement.executeQuery("SELECT Brand_ID FROM [BikeBrands] WHERE BrandName = " + newData.getBikeBrand().toString() + ";");
                if (resultSet.next())
                    pK = resultSet.getInt(1);
                newData.getBikeBrand().setPrimaryKey(pK);
            }
            statement.executeUpdate("INSERT INTO [" + tableName + "] VALUES(" + newData.toString() + ")");
            resultSet = statement.executeQuery("SELECT IDENT_CURRENT ('Bike') AS Current_Identity;");
            if (resultSet.next())
                pK = resultSet.getInt(1);
            newData.setPrimaryKey(pK);
            BikeRepository.getInstance().getData().add(newData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String tableName, Bike updatedBike) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("UPDATE BIKE");
        } catch (SQLException e) {

        }
    }

    public static void update(RentalInvoice ri) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("update RentalInovice set " + ri.update() + " where  Rental_ID = " + ri.getRentID() + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void saveTo(String tableName, User newData) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("INSERT INTO [" + tableName + "] VALUES(" + newData + ")");
            resultSet = statement.executeQuery("SELECT User_ID FROM [User] WHERE User_ID = @@Identity;");
            if (resultSet.next())
                newData.setPrimaryKey(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveTo(String tableName, RentalInvoice newData) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("INSERT INTO RentalInovice(User_ID,Bike_ID,StartTime) VALUES(" + newData + ");");
            resultSet = statement.executeQuery("SELECT Rental_ID FROM RentalInovice WHERE Rental_ID = @@Identity;");
            if (resultSet.next())
                newData.setRentID(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

