package ciaworldfactbook;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class WFBConnections {

	private static Scanner in;
	
	public static URLConnection OpenUrlConnection() throws IOException {
		
		URL url = new URL("https://www.cia.gov/library/publications/the-world-factbook" +
				"/print/textversion.html");
	      URLConnection connection = url.openConnection();
		return connection;
	}

	public static Scanner StreamConnection(URLConnection connection) throws IOException {
		
		InputStream instream = connection.getInputStream();
	    in = new Scanner(instream);
		return in;
	}
	
	public static String CountryHTML(String code) throws IOException {

		URL url = new URL(
				"https://www.cia.gov/library/publications/the-world-factbook"
						+ "/geos/countrytemplate_" + code + ".html");

		URLConnection connection = url.openConnection();
		StreamConnection(connection);

		String data = null;
		while (in.hasNextLine()) {
			data = in.nextLine();
			data = data.trim();
		}
		return data;
	}
	
	public static String getCountryFact(String countryCode, String category) throws IOException {
		
		
            URL url = new URL(
                    "https://www.cia.gov/library/publications/the-world-factbook"
                    + "/geos/countrytemplate_" + countryCode + ".html");

            URLConnection connection = url.openConnection();
            Scanner scan = StreamConnection(connection);
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

    public static String getCategoryData(String input) throws IOException 
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
            System.out.println(data);
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
