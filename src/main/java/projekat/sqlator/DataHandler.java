package projekat.sqlator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DataHandler
{
    private static final String URL_PREFIX = "jdbc:sqlite:";
    
    /**
     * Attempts to create a new database file at the designated path
     * @param path full path (filename included) to the new files location
     * @return true if created database successfully, false otherwise
     */
    public static boolean createNewDatabase(String path)
    {
        String url = URL_PREFIX + path;
        
        try (Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null)
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("createNewDatabase: Database driver name is " + meta.getDriverName());
                System.out.println("createNewDatabase: A new database has been successfully created.");
                return true;
            }
            
            return false;
        }
        catch (SQLException e)
        {
            System.out.println("createNewDatabase: " + e.toString());
            return false;
        }
    }
    
    /**
     * Creates a table in the designated database
     * @param path which database to create a table in
     * @param sql the table creation sql code
     * @return true if table is created successfully
     */
    public static boolean createTable(String path, String sql)
    {
        String url = URL_PREFIX + path;
        
        try (Connection conn = DriverManager.getConnection(url); Statement statement = conn.createStatement())
        {
            statement.execute(sql);
            System.out.println("createTable: A new table has been successfully added.");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("createTable:" + e.toString());
            return false;
        }
    }
    
    /**
     * Deletes a table in the designated database
     * @param path which database to delete a table from
     * @param tableName the name of the table to delete
     * @return true if table is dropped successfully
     */
    public static boolean dropTable(String path, String tableName)
    {
        String url = URL_PREFIX + path;
        String sql = "DROP TABLE IF EXISTS " + tableName;
        
        try (Connection conn = DriverManager.getConnection(url); Statement statement = conn.createStatement())
        {
            statement.execute(sql);
            System.out.println("dropTable: The table has been successfully deleted.");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("dropTable:" + e.toString());
            return false;
        }
    }
    
    /**
     * Returns the tables inside a database file
     * @param path path to the database file
     * @return the resulting ArrayList of strings containing table names
     */
    public static ArrayList<String> getTables(String path)
    {
        String sql = "SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%'";
        ArrayList<String> result = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); Statement statement = conn.createStatement())
        {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                result.add(rs.getString("name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("getTables:" + e.toString());
            return null;
        }
        
        return result;
    }
    
    /**
     * Gets the ArrayList of fields contained inside a table
     * @param path path to the database file
     * @param table name of the table youre getting fields from
     * @return the resulting ArrayList of strings containing field names
     */
    public static Vector<String> getFields(String path, String table)
    {
        String sql = "PRAGMA table_info(" + table + ");";
        Vector<String> result = new Vector<>();
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); Statement statement = conn.createStatement())
        {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                result.add(rs.getString("name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("getTables:" + e.toString());
            return null;
        }
        
        return result;
    }
    
    /**
     * Selects all rows from a given table
     * @param path path to the database file
     * @param table name of the table you're selecting from
     * @return A 2D Matrix vector, where each row is inside a vector object
     */
    public static Vector<Vector<Object>> selectAll(String path, String table)
    {
        String sql = "SELECT * FROM " + table;
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); Statement statement = conn.createStatement())
        {
            ResultSet rs = statement.executeQuery(sql);
            Vector<Vector<Object>> selectionResult = new Vector<Vector<Object>>();
            
            int i = 1;
            while (rs.next())
            {
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                Vector<Object> row = new Vector();
                
                for (int j = 1; j <= columnCount; j++)
                {
                    row.add(rs.getObject(j));
                }

                selectionResult.add(row);
                i++;
            }
            
            return selectionResult;
        }
        catch (SQLException e)
        {
            System.out.println("selectAll:" + e.toString());
            return null;
        }
    }
}
