package ciaworldfactbook;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
	



	




}
