package projekat.sqlator;

public class SqlTableField
{
    private String name;
    private SqlType type;
    private boolean notNull;
    private boolean primaryKey;
    private boolean autoIncrement;
    private boolean unique;
    
    private String sqlCode;
    
    /**
     * Contains properties of a field meant to be contained in a table.
     * @param name Name of the field.
     * @param type Datatype of the field.
     * @param notNull If the field can not be nullable.
     * @param primaryKey If the field is a primary key.
     * @param autoIncrement If the field auto increments. This sets the type to INTEGER, and primaryKey to true.
     * @param unique If the field is meant to have unique values.
     */
    public SqlTableField(String name, SqlType type, boolean notNull, boolean primaryKey, boolean autoIncrement, boolean unique)
    {
        setName(name);
        setType(type);
        setNotNull(notNull);
        setPrimaryKey(primaryKey);
        setAutoIncrement(autoIncrement);
        setUnique(unique);
    }

    private void generateSqlCode()
    {
        String generated = "\"" + name + "\"\t" + type.name();
        
        if (notNull)
            generated += " NOT NULL";
        if (primaryKey)
            generated += " PRIMARY KEY";
        if (autoIncrement)
            generated += " AUTOINCREMENT";
        if (unique)
            generated += " UNIQUE";
        
        sqlCode = generated;
    }

    public final void setName(String name)
    {
        this.name = name;
    }

    // Does not allow the setting of type if autoincrement is enabled
    public final void setType(SqlType type)
    {
        if (!autoIncrement)
            this.type = type;
    }

    public final void setNotNull(boolean notNull)
    {
        this.notNull = notNull;
    }

    public final void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    // This method is set to automatically disable and enable private key until its implemented properly
    public final void setAutoIncrement(boolean autoIncrement)
    {
        this.autoIncrement = autoIncrement;
        this.primaryKey = autoIncrement;
        if (autoIncrement)
            type = SqlType.INTEGER;
    }

    public final void setUnique(boolean unique)
    {
        this.unique = unique;
    }
    
    /**
     * Returns the sql code for generating the table field.
     * @return generated table sql code.
     */
    public String getSqlCode()
    {
        generateSqlCode();
        return sqlCode;
    }
    
    public String getName()
    {
        return name;
    }

    public SqlType getType()
    {
        return type;
    }

    public boolean isNotNull()
    {
        return notNull;
    }

    public boolean isPrimaryKey()
    {
        return primaryKey;
    }

    public boolean isAutoIncrement()
    {
        return autoIncrement;
    }

    public boolean isUnique()
    {
        return unique;
    }
}
