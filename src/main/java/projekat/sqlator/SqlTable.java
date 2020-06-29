package projekat.sqlator;

import java.util.ArrayList;

public class SqlTable
{
    // TODO: This is just bad, find another solution other than public :(
    // It's public so that a fields property can be changed
    public ArrayList<SqlTableField> fieldList = new ArrayList<>();
    private String name;
    
    /**
     * Stores a list of table fields.
     * @param name Name of the table.
     */
    public SqlTable(String name)
    {
        this.name = name;
    }
    
    public void setName(String name)
    {
        // Brise sav whitespace iz imena tabele, jer se desi greska ako se kreira tabela sa razmakom u imenu
        this.name = name.replaceAll("\\s","");
    }
    
    public String getName()
    {
        return name;
    }
    
    public void addField(SqlTableField field)
    {
        fieldList.add(field);
    }
    
    public void removeField(int index)
    {
        fieldList.remove(index);
    }
    
    public boolean hasPrimaryKey()
    {
        for (SqlTableField field : fieldList)
        {
            if (field.isPrimaryKey())
                return true;
        }
        
        return false;
    }
    
    public String getSqlCode()
    {
        String generated = "CREATE TABLE \"" + name + "\" (";
        
        for (int i = 0; i < fieldList.size(); i++)
        {
            generated += "\n\t" + fieldList.get(i).getSqlCode();
            if (i+1 < fieldList.size())
            {
                generated += ",";
            }
        }
        
        return generated + "\n);";
    }
}
