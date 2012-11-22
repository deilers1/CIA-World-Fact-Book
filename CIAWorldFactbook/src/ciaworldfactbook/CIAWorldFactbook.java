/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ciaworldfactbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Devon
 */
public class CIAWorldFactbook 
{
    ArrayList<String> countryCommunicationsList;
    
    public CIAWorldFactbook()
    {
        countryCommunicationsList = new ArrayList<String>();
    }
    
    public ArrayList<String> populateCountryCommunicationsList() throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("HeaderDocuments/COUNTRY_COMMUNICATIONS.txt"));
        
        
        while (scan.hasNext())
        {
            countryCommunicationsList.add(scan.nextLine().trim());
        }
        return countryCommunicationsList;
    }
    
    public String[] getCountryCommunicationList() throws FileNotFoundException
    {
        ArrayList<String> temp = new ArrayList<String>();
        
        temp = populateCountryCommunicationsList();
        String[] temp1 = new String[temp.size()];
        
        
        for (int i = 0; i < temp.size(); ++i)
        {
            temp1[i] = temp.get(i);
        }
        
        return temp1;
    }
}
