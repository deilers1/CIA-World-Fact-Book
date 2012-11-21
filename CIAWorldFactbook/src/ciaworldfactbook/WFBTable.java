/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ciaworldfactbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


public class WFBTable {

	private static final String country = null;
	static String CountryCode = null; 
        
    
    /**
     * contains key, value references of countries and their country codes
     * 
     * this shouldn't be declared as static variables, 
     * because static is only for single instance programs
     */
    public HashMap<String, String> countryCodeList = new HashMap<String, String>();
    
    /**
     * holds the names of all countries
     * it was made an instance variable for a better scope of accessibility
     * 
     */
    private  ArrayList<String> countryList;
    
    
    private String CountryName = null;
    private String endTag = "</option>";
    private int endTaglen = endTag.length(); // length is 9
    private int space = 1;
//    private String startTag = "<option value="+'"'+"xx"+'"'+">";
//    private int startTaglen = startTag.length(); // length is 19 
    private final int STARTTAGLEN = 19;
	
    /**
     * 
     * constructor for world fact book table
     * so far all it does is populate the country list
     */
    public WFBTable() throws IOException
    {
        CreateHashMap(
			   WFBConnections.StreamConnection(
					   WFBConnections.OpenUrlConnection()));
        
        		countryList = new ArrayList<String>(countryCodeList.values());
		Collections.sort(countryList);
    }
    
    
	public Scanner CreateHashMap(Scanner in) {
		
		//Store country code and name into a table
	      while (in.hasNextLine())
	      {
	         String input = in.nextLine();
	         if(input.contains(endTag)){
	        	 input = input.trim(); //System.out.println(input);
	        	 CountryCode = input.substring(15, 17);
	        	 String tempLine = input.substring(STARTTAGLEN + 1); 
	        	 int linelen = tempLine.length();
	        	 CountryName = tempLine.substring(0, (linelen - endTaglen - space));
	        	 countryCodeList.put(CountryCode, CountryName);
	        	 countryCodeList.remove("d "); //had to remove this key, value is unnecessary and invaluable
	         }
	      }
	      //System.out.println(countryList);
		return in;
		
	}
	
	public String printList() {
		
		ArrayList<String> list = new ArrayList<String>(countryCodeList.values());
		Collections.sort(list);
		for (String country : list) {
			System.out.println(country);
		}
		
		return country;
	}

	public String SelectCountry(String command) throws IOException {
		
		//iterate over countryList table
	      for (Entry<String, String> entry : countryCodeList.entrySet()) {
	    	  
			if(command.equalsIgnoreCase(entry.getValue())){
	    		WFBConnections.CountryData(entry.getKey());
	    	  	} 
	    	  }
	      
		return command;    
	}
	
	public static String HTML_Tags() throws FileNotFoundException{
		
		ArrayList<String> taglist = new ArrayList<String>();
		File file = new File("html_tags.txt");
		FileReader f1 = new FileReader(file);
		
		Scanner in = new Scanner(f1);
			
		while(in.hasNextLine()){
			String line = in.nextLine();
			taglist.add(line);
		}
		
		String tag = null;
		for (String string : taglist) {
			tag = string;
		}
		return tag;
	}
	
        public ArrayList<String> getCountries()
        {
            return countryList;
        }
	
}

