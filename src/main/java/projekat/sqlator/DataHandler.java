package projekat.sqlator;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
     * @throws SQLException An exception that occured during the sql execution
     */
    public static void createTable(String path, String sql) throws SQLException
    {
        String url = URL_PREFIX + path;
        
        try (Connection conn = DriverManager.getConnection(url); Statement statement = conn.createStatement())
        {
            statement.execute(sql);
            System.out.println("createTable: A new table has been successfully added.");
        }
        catch (SQLException e)
        {
            System.out.println("createTable:" + e.toString());
            throw e;
        }
    }
    
    public static void executeSql(String path, String sql) throws SQLException
    {
        String url = URL_PREFIX + path;
        
        try (Connection conn = DriverManager.getConnection(url); Statement statement = conn.createStatement())
        {
            statement.execute(sql);
            System.out.println("executeSql: Custom SQL code has been successfully executed.");
        }
        catch (SQLException e)
        {
            System.out.println("executeSql:" + e.toString());
            throw e;
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
     * Returns the table names inside a database file
     * @param path path to the database file
     * @return the resulting ArrayList of strings containing table names
     */
    public static ArrayList<String> getTableNames(String path)
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
    public static Vector<SqlTableField> getFields(String path, String table)
    {
        String sql = "PRAGMA table_info(" + table + ");";
        Vector<SqlTableField> result = new Vector<>();
        
        Vector<String> uniqueFields = checkUniqueColumn(path, table);
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); Statement statement = conn.createStatement())
        {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                SqlTableField field = new SqlTableField();
                field.setName(rs.getString("name"));
                field.setType(SqlType.valueOf(rs.getString("type")));
                field.setNotNull(rs.getBoolean("notnull"));
                field.setPrimaryKey(rs.getBoolean("pk"));
                
                if (uniqueFields.contains(field.getName()))
                    field.setUnique(true);
                
                result.add(field);
            }
        }
        catch (SQLException e)
        {
            System.out.println("getTables:" + e.toString());
            return null;
        }
        
        // SVE DAJE POSLEDNJI COLUMN NECE DA UCITA TABELU POPRAVI PLS STO NE RADI AAAAAAAAA
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
    
    /**
     * Deletes the row by condition from the table
     * @param path path to the database file
     * @param table name of the table you're deleting from
     * @param condition sql condition you wish to apply, the part after the "WHERE" sql command
     * @throws SQLException An exception that occured during the sql execution
     */
    public static void deleteRow(String path, String table, String condition) throws SQLException
    {
        String sql = "DELETE FROM " + table + " WHERE " + condition + ";";
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("deleteRow:" + e.toString());
            throw e;
        }
    }
    
    /**
     * Updates a row in the table using a specific condition
     * @param path path to the database file
     * @param table name of the table you're deleting from
     * @param condition sql condition you wish to apply, the part after the "SET" sql command. Must include "WHERE".
     * @throws SQLException An exception that occured during the sql execution
     */
    public static void updateRow(String path, String table, String condition) throws SQLException
    {
        String sql = "UPDATE " + table + " SET " + condition + ";";
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("deleteRow:" + e.toString());
            throw e;
        }
    }
    
    // Finding out which column has a unique value indentifier, returns list of columns which have the unique identifier
    public static Vector<String> checkUniqueColumn(String path, String table)
    {
        String sql_pragma_list = "PRAGMA index_list(" + table + ");";
        String sql_pragma_info = "PRAGMA index_info(replace_me);";
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); Statement statement = conn.createStatement())
        {
            ResultSet rs = statement.executeQuery(sql_pragma_list);
            Vector<String> unique_fields = new Vector<String>();
            Vector<String> sql_pragma_info_list = new Vector<String>();
            
            // Get the column autoindex names of unique fields
            while (rs.next())
            {
                String temp_table = rs.getString("name");
                int is_unique = rs.getInt("unique");
                
                if (is_unique == 1)
                {
                    sql_pragma_info_list.add(sql_pragma_info.replace("replace_me", temp_table));
                }
            }
            
            // Get the field names of unique values
            for (String sql_command : sql_pragma_info_list)
            {
                ResultSet rs2 = statement.executeQuery(sql_command);
                while (rs2.next())
                {
                    unique_fields.add(rs2.getString("name"));
                }
            }
            
            return unique_fields;
        }
        catch (SQLException e)
        {
            System.out.println("selectAll:" + e.toString());
            return null;
        }
    }
    
    public static void newRow(String path, String table) throws SQLException
    {
        String sql = "INSERT INTO \"" + table + "\" DEFAULT VALUES";
        
        try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            if (e.getMessage().contains("NOT NULL constraint failed"))
            {
                Vector<SqlTableField> fields = DataHandler.getFields(path, table);
                
                sql = "INSERT INTO \"" + table + "\"(";
                String values = "VALUES(";
                
                int i = 0;
                for (SqlTableField field : fields)
                {
                    sql += field.getName();
                    if (field.isNotNull())
                    {
                        values += "0";
                    }
                    else
                    {
                        values += "NULL";
                    }
                    
                    if (i < fields.size()-1)
                    {
                        sql += " , ";
                        values += " , ";
                    }
                    i++;
                }
                
                values += ");";
                sql += ") " + values;
                
                try (Connection conn = DriverManager.getConnection(URL_PREFIX + path); PreparedStatement pstmt = conn.prepareStatement(sql))
                {
                    pstmt.executeUpdate();
                    return;
                }
                catch (SQLException ex)
                {
                    System.out.println("newRow, NOT NULL failed:" + ex.toString());
                    throw ex;
                }
            }
            
            System.out.println("newRow:" + e.toString());
            throw e;
        }
    }
}
