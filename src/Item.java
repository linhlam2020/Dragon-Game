/*
*@description Item class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 23, 2017
*/

public class Item 
{
    private String name;
    private String type;
    private String desc;

    // A constructor that takes three parameters and
    // sets the variables accordingly. Parameters must
    // be in order: name, type, description.
    public Item( String Name, String Type, String Description)
    {
        name = Name;
        type = Type;
        desc = Description;
    }


    // This constructor takes no parameters and
    // sets variables to arbitrary values.
    public Item( )
    {
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

    public void setDescription( String iDesc )
    {
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

    // To String method: set the template to prints out the information about the item.
    public String toString( )
    {
        return "Item:" + "\tShortname: " + getName() + "\tType: " + getType() + "\tDescription: " + getDescription();
    }

    public void print() {
        System.out.println( "Item:" );
        System.out.println( String.format("\t ShortName: %s", name) );
        System.out.println( String.format("\t Type: %s", type) );
        System.out.println( String.format("\t Description: %s", desc) );
    }
}
