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
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Devon
 */
public class CIAWorldFactbook 
{
    public HashMap<String,String> countryFactTypeList;
    public HashMap<String,String> worldFactTypeList;
    private HashMap<String, String> countryCodeList;   
    /**
     * Loads country and world fact types into HashMaps, so when a category is selected,
     * the corresponding list will be loaded appropriately
     */
    public CIAWorldFactbook() throws FileNotFoundException, IOException
    {

        
        countryFactTypeList = populateFactTypeList("HeaderDocuments/CountryFactTypes.txt");
        worldFactTypeList = populateFactTypeList("HeaderDocuments/WorldFactTypes.txt");
        countryCodeList = new HashMap<String,String>();
        createCountryCodeList();
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
    
    
    private void createCountryCodeList() throws IOException
    {
            String CountryName;
            String endTag = "</option>";
            int endTaglen = endTag.length(); // length is 9
            int space = 1;
            final int STARTTAGLEN = 19;
            String CountryCode;
            
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
            boolean isExchangeRate =false;
            
            if(category.equalsIgnoreCase("exchange rates"))
            {
                isExchangeRate=true;
            }
            
            String[] specialCases = {"area", "capital", "nationality", "median age", "urbanization", "sex ratio", "infant mortality rate", "Life expectancy at birth", "school life expectancy (primary to tertiary education)", "executive branch", "national anthem" };
            String[] specialCaseIdentifiers = {"total:", "name:", "noun:", "total:", "urban population:", "at birth:", "total:", "total population:", "total:", "chief of state:", "name:" };
            
        while (scan.hasNextLine()) 
        {
            for (int i = 0; i < specialCases.length; ++i)
            {
                String s = specialCases[i];
                if (s.equalsIgnoreCase(category))
                {
                    while (scan.hasNextLine())
                    {
                        if (line.contains(category+"</a>"))
                        {
                            while (scan.hasNextLine()) 
                            {
                                data = getSpecialData(line, specialCaseIdentifiers[i]);
                                if (data != null) {
                                    factFound = true;
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
                    

                    if (data == null)
                    {
                        data = "Data not found";
                        factFound = true;
                        break;
                    }
                }
            }
            
            if (!factFound && line.contains(category + "</a>")) 
            {
                while (scan.hasNextLine()) {
                    if (line.contains("<div class=\"category_data\">") && !isExchangeRate) 
                    {
                        data = getCategoryData(line);
                        factFound = true;
                        // ends the current connection
                        break;
                    }
                    else if (category.equalsIgnoreCase("exchange rates"))
                    {
                        data = getCategoryData(line);
                        if (data != null && data.equals("1 October - 30 September"))
                        {
                            data = "No data found";
                            factFound = true;
                            break;
                        }
                        else if (data != null)
                        {
                            scan.nextLine();
                            line = scan.nextLine().trim();
                            
                            data += "\n\n";
                            data += getCategoryDataCustomTags(line, "<div class=\"category_data\" style=\"padding-top: 3px;\">", "</div>");
                            factFound = true;
                            break;
                        }
                    }
                    else if (category.equalsIgnoreCase("elevation extremes"))
                    {
                        String start = "lowest point: ";
                        data = getSpecialData(line, "lowest point:");
                        if (data != null)
                        {
                            data = start + data+"\n";
                            while(scan.hasNextLine())
                            {
                                String temp = getCategoryDataCustomTags(line, "<span class=\"category_data\" style=\"font-weight:normal; vertical-align:top;\">", "</span>");
                                
                                if (temp != null)
                                {
                                    data = data + "highest point: " + temp;
                                    factFound = true;
                                    break;
                                }
                                line = scan.nextLine().trim();
                            }
                        }
                    }
                    if (factFound)
                    {
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
        
        if (startTagIndex != -1 && endTagIndex == -1)
        {
            endTagIndex = input.length();
        }
        
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
    
     private static String getCategoryDataCustomTags(String input, String startTag, String endTag) throws IOException 
    {

        String data = null;
        int startTagIndex;
        int endTagIndex;

        startTagIndex = indexOfIgnoreCase(input, startTag);
        endTagIndex = indexOfIgnoreCase(input, endTag);
        
        if (startTagIndex != -1 && endTagIndex == -1)
        {
            endTagIndex = input.length();
        }
        
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
        
        public static String getAreaData(String input) {
		
		String startTag = "<div class=\"category\">";
	    int startTaglength = startTag.length();
		
	    String leftArrow = "<";
	    String rightArrow = ">";
            String total = null;
            String area = null;
	    if(input.contains("total:")){
			int leftArrowindex = input.indexOf(leftArrow, 1);
			int rightArrowindex = input.indexOf(rightArrow, leftArrowindex);
			int nextRAindex = input.indexOf(leftArrow, rightArrowindex);
			
			total = input.substring(startTaglength, leftArrowindex);
			area = input.substring(rightArrowindex + 1, nextRAindex);
		}
            if (total==null && area==null)
            {
                return null;
            }
            else
            {
                return total+area;

            }
		   
	}
        public static String getSpecialData(String input, String identifier) {
		
		String startTag = "<div class=\"category\">";
	    int startTaglength = startTag.length();
		
	    String leftArrow = "<";
	    String rightArrow = ">";
            String capital = null;

	    if(input.contains(identifier)){
			int leftArrowindex = input.indexOf(leftArrow, 1);
			int rightArrowindex = input.indexOf(rightArrow, leftArrowindex);
			int nextRAindex = input.indexOf(leftArrow, rightArrowindex);
			
			capital = input.substring(rightArrowindex + 1, nextRAindex);
		}

 
                return capital;

           
		   
	}
        
}
