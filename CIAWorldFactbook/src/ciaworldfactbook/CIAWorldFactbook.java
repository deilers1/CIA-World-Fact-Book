/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ciaworldfactbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Devon
 */
public class CIAWorldFactbook 
{
    public HashMap<String,String> factTypeList;
    private HashMap<String, String> countryCodeList;   
    /**
     * Constructor in which populates string arrays to be loaded into a JList
     */
    public CIAWorldFactbook() throws FileNotFoundException, IOException
    {

        
        factTypeList = populateFactTypeList("HeaderDocuments/CountryFactTypes.txt");
        countryCodeList = new HashMap<String,String>();
        createCountryKeyList();
    }
        


    
    private static HashMap<String, String> populateFactTypeList(String fileName) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(fileName));
        HashMap<String, String> list = new HashMap<String, String>();
        
        while (scan.hasNext())
        {
            list.put(scan.nextLine().trim(), scan.nextLine().trim());
        }
        
        return list;
    }
    
    public HashMap<String,String> getCountryCodeList()
    {
        return countryCodeList;
    }
    
    private void createCountryKeyList() throws IOException
    {
            String CountryName = null;
            String endTag = "</option>";
            int endTaglen = endTag.length(); // length is 9
            int space = 1;
            final int STARTTAGLEN = 19;
            String CountryCode = null;
            
        URL url = new URL("https://www.cia.gov/library/publications/the-world-factbook" +
				"/print/textversion.html");
	URLConnection connection = url.openConnection();
        InputStream instream = connection.getInputStream();
        
        
        Scanner in = new Scanner(instream);
        
        	      while (in.hasNextLine())
	      {
	         String input = in.nextLine();
	         if(input.contains(endTag)){
	        	 input = input.trim(); //System.out.println(input);
	        	 CountryCode = input.substring(15, 17);
	        	 String tempLine = input.substring(STARTTAGLEN + 1); 
	        	 int linelen = tempLine.length();
	        	 CountryName = tempLine.substring(0, (linelen - endTaglen - space));
                         countryCodeList.put(CountryName, CountryCode);
	        	 countryCodeList.remove("d "); //had to remove this key, value is unnecessary and invaluable
	         }
	      }
    }
    
    	public String getCountryFact(String countryCode, String category) throws IOException {
		
		
            URL url = new URL(
                    "https://www.cia.gov/library/publications/the-world-factbook"
                    + "/geos/countrytemplate_" + countryCode + ".html");

            URLConnection connection = url.openConnection();
            Scanner scan = WFBConnections.StreamConnection(connection);
            String line = scan.nextLine();

            String data = null;
            boolean factFound = false;
	    
        while (scan.hasNextLine()) 
        {
            if (line.contains(category + "</a>")) 
            {
                while (scan.hasNextLine()) {
                    if (line.contains("<div class=\"category_data\">")) 
                    {
                        data = getCategoryData(line);
                        factFound = true;
                        // ends the current connection
                        break;
                    }
                    line = scan.nextLine().trim();
                }
            }
            if (factFound) 
            {

                break;
            }
            line = scan.nextLine().trim();
        }

        return data;
    }
        
    private static String getCategoryData(String input) throws IOException 
    {

        String data = null;
        String startTag = "<div class=\"category_data\">";
        String endTag = "</div>";
        int startTagIndex;
        int endTagIndex;

        startTagIndex = indexOfIgnoreCase(input, startTag);
        endTagIndex = indexOfIgnoreCase(input, endTag);

        if (startTagIndex != -1 && endTagIndex != -1) 
        {
            data = input.substring(startTagIndex + startTag.length(), endTagIndex);
            if (data == null) 
            {
                System.out.println("No data found.");
            }
        }

        return data;
    }
    
        public static int indexOfIgnoreCase(String textToSearch, String pattern, int fromIndex) 
    {

        int patternLen = pattern.length();

        while (textToSearch.length() > ((fromIndex + patternLen) - 1)) 
        {
            if (textToSearch.regionMatches(true, fromIndex, pattern, 0, patternLen)) 
            {
                return fromIndex;
            }
            fromIndex++;
        }
        return -1;
    }

	public static int indexOfIgnoreCase(String textToSearch, String pattern) {
	      return indexOfIgnoreCase(textToSearch, pattern, 0);
	   }
}
