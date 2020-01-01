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

    public static void delete(User selected) {
        if (selected == null)
            return;
        else
            try {
                Statement statement = instance.connection.createStatement();
                String deleteSQL = "DELETE FROM [User] WHERE User_ID = ";
                var i = statement.executeUpdate(deleteSQL + selected.getPrimaryKey() + ";");
            } catch (SQLException e) {
                System.out.println("DELETE statement failed!");
                System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());
            }
    }

    public static void delete(RentalInvoice selected) {
        if (selected == null)
            return;
        else
            try {
                Statement statement = instance.connection.createStatement();
                String deleteSQL = "DELETE FROM RentalInovice WHERE Rental_ID = ";
                var i = statement.executeUpdate(deleteSQL + selected.getRentID() + ";");
            } catch (SQLException e) {
                System.out.println("DELETE statement failed!");
                System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());
            }

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
            if (!Database.contains(newData, "Category")) {
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
            if (!Database.contains(newData, "Brand")) {
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
            statement.executeUpdate("INSERT INTO [" + tableName + "] VALUES(" + newData.toString() + ",null);");
            resultSet = statement.executeQuery("SELECT IDENT_CURRENT ('Bike') AS Current_Identity;");
            if (resultSet.next())
                pK = resultSet.getInt(1);
            newData.setPrimaryKey(pK);
            BikeRepository.getInstance().getData().add(newData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean contains(Bike newData, String category) {
        try {
            Statement statement = instance.connection.createStatement();
            if (category.equals("Category")) {
                resultSet = statement.executeQuery("select B.Bike_ID, BC.CategoryName from Bike B join BikeCategory BC on B.Category_ID = BC.Category_ID where BC.CategoryName = '" + newData.getCategory() + "';");
                if (!resultSet.next()) {
                    resultSet = statement.executeQuery("select CategoryName  from BikeCategory where CategoryName = '" + newData.getCategory() + "';");
                    if (!resultSet.next())
                        return false;
                }
            } else {
                resultSet = statement.executeQuery("select B.Bike_ID, BB.BrandName from Bike B join BikeBrands BB on B.Brand_ID = BB.Brand_ID where BB.BrandName = '" + newData.getBrand() + "';");
                if (!resultSet.next()) {
                    resultSet = statement.executeQuery("select BrandName from BikeBrands where BrandName = '" + newData.getBrand() + "';");
                    if (!resultSet.next())
                        return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return true;
    }


    public static void saveTo(String tableName, User newData) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("INSERT INTO [" + tableName + "] VALUES(" + newData + ")");
            resultSet = statement.executeQuery("SELECT U.User_ID , RI.Bike_ID FROM [User] U join RentalInovice RI on U.User_ID = RI.User_ID WHERE U.User_ID = @@Identity");
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

    public static void update(User u) {
        try {
            Statement statement = instance.connection.createStatement();
            System.out.println("update [User] set " + u.update() + " where  User_ID = " + u.getPrimaryKey() + ";");
            statement.executeUpdate("update [User] set " + u.update() + " where  User_ID = " + u.getPrimaryKey() + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void update(Bike updatedBike) {
        try {
            Statement statement = instance.connection.createStatement();
            System.out.println("update bike");
            statement.executeUpdate("UPDATE BIKE set " + updatedBike.update() + " where Bike_ID = " + updatedBike.getPrimaryKey() + ";");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
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

    /**
     * This method verify if a brand already exists in the database
     *
     * @param newValue
     * @return the index of the newValue brand or -1 if not found
     */
    public static Integer getKeyOf(String newValue) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("select bb.Brand_ID , b.Price_ID from BikeBrands bb join Bike b on bb.Brand_ID = b.Brand_ID where BrandName = '" + newValue + "';");

        } catch (SQLException e) {

        }
        return -1;
    }

    public static void delete(Bike selected) {
        if (selected == null)
            return;
        else
            try {
                Statement statement = instance.connection.createStatement();
                String deleteSQL = "DELETE FROM Bike WHERE Bike_ID = ";
                var i = statement.executeUpdate(deleteSQL + selected.getPrimaryKey() + ";");
            } catch (SQLException e) {
                System.out.println("DELETE statement failed!");
                System.out.println(e.getStackTrace());
                System.out.println(e.getMessage());
            }
    }

    public static void update(BikePrice b) {
        try {
            Statement statement = instance.connection.createStatement();
            statement.executeUpdate("UPDATE Price set PricePerHour = "+b.getPrice()+" where Price_ID = (select b.Price_ID from Bike b where b.Bike_ID = "+b.getPk() +"); ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }


    public void load(int option, SearchResultRepository data) {
        if (!data.getInstance().getData().isEmpty())
            return;
        try {
            Statement statement = instance.connection.createStatement();
            String selectSql = generateSelectByOption(option);
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 1; i <= 2; i++) {
                    attributes.add(resultSet.getString(i));
                }
                data.getInstance().getData().add(new SearchResult(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateSelectByOption(int option) {
        switch (option) {
            case SearchController.LOADRENTS2HOURS:
                return "SELECT U.First_Name + ' ' + U.Second_Name AS Name, U.City FROM [User] U WHERE User_ID IN (SELECT DISTINCT RI.User_ID FROM RentalInovice RI WHERE datediff(HOUR , RI.StartTime  ,RI.EndTime)> 2)";
            case SearchController.RENTSBYCATEGORY:
                return "SELECT DISTINCT BC.CategoryName, (SELECT COUNT(*) FROM RentalInovice RI join Bike B2 on RI.Bike_ID = B2.Bike_ID where b2.Category_ID = BC.Category_ID ) AS Inchirieri FROM Bike B JOIN BikeCategory BC on B.Category_ID = BC.Category_ID order by Inchirieri desc";
            case SearchController.RENTSBYUSERS:
                return "SELECT U.First_Name +' '+ U.Second_Name AS Name, (SELECT COUNT(*) FROM RentalInovice RI WHERE RI.User_ID = U.User_ID) AS 'Numar Inchirieri' FROM  [User] U;";
            case SearchController.RENTSOFROADBIKES:
                return "select  U.First_Name + ' ' + U.Second_Name AS Name, U.City from [User] U, (select RI.User_ID FROM RentalInovice RI join Bike B on RI.Bike_ID = B.Bike_ID join BikeCategory BC on B.Category_ID = BC.Category_ID WHERE BC.CategoryName = 'Road') AS RI_UID WHERE U.User_ID = RI_UID.User_ID;";
        }
        return null;
    }

    public void loadFrom(BikePriceRepository data) {
        if (!data.getInstance().getData().isEmpty())
            return;
        int columnsNumber = 0;

        try {
            Statement statement = instance.connection.createStatement();
            String selectSql = "select Bike_ID,bb.BrandName,BC.CategoryName,b.Color,P.PricePerHour from Bike b join BikeBrands BB on b.Brand_ID = BB.Brand_ID join BikeCategory BC on b.Category_ID = BC.Category_ID join Price P on b.Price_ID = P.Price_ID";
            resultSet = statement.executeQuery(selectSql);

            ResultSetMetaData rsmd = resultSet.getMetaData();
            columnsNumber = rsmd.getColumnCount();

            // Print results from select statement
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    attributes.add(resultSet.getString(i));
                }
                data.getInstance().getData().add(new BikePrice(attributes.toArray(String[]::new)));
                attributes.clear();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

