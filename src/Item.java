/**
*@description Item class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 9, 2017
*/

public class Item 
{
	private String name; 
	private String type;
	private String description;
		
	// A constructor that takes three parameters and
	// sets the values accordingly. Parameters must
	// be in order: name, type, description.
	
	public Item( String iName, String iType, String iDescription)
	{
		name = iName;
		type = iType;
		description = iDescription;
	}
	
	
	// This constructor takes no parameters and
	// sets variables to arbitrary values.
	public Item( )
	{
		name = "stick";
		type = "tool";
		description = "a long bamboo stick";
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
	
	public void setDescription( String iDescription )
	{
		description = iDescription;
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
		return description;
	}
	
	
	// To String method: set the template to prints out the information about the item.
	public String toString( )
	{
		return "Item:\n" + "Shortname: " + name + "\n" + "      "+ "Type: " + type + "\n" + "Description: " + description + "\n";
	}

}
