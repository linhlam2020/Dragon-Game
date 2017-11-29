import java.io.Serializable;

/*
*@description Item class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version November 29, 2017
*/

public class Item  implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String name;
    private String type;
    private String desc;

    // A constructor that takes three parameters and sets the variables accordingly.
    // Parameters must be in order: name, type, description.
    public Item( String Name, String Type, String Description ) {
        name = Name;
        type = Type;
        desc = Description;
    }


    // This constructor takes no parameters and sets variables to arbitrary values.
    public Item() {
        name = null;
        type = null;
        desc = null;
    }


    // Set methods for each variable.
    public void setName( String iName )
    {
        name = iName;
    }

    public void setType( String iType )
    {
        type = iType;
    }

    public void setDescription( String iDesc ) {
        desc= iDesc;
    }


    // Get methods for each variable.
    public String getName( )
    {
        return name;
    }

    public String getType( )
    {
        return type;
    }

    public String getDescription( )
    {
        return desc;
    }


    public String toString( ) {
        return "\t\tShortname: " + getName() + "\n\t\tType: " + getType( ) + "\n\t\tDescription: " + getDescription();
    } 

    public void print() {
        System.out.println( String.format("\tShortName: %s", name) );
        System.out.println( String.format("\tType: %s", type) );
        System.out.println( String.format("\tDescription: %s\n", desc) );
    }
}