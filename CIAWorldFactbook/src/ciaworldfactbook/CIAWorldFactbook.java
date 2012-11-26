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
    // these are made public for easier access
    
    public String[] countryCommunicationsList;
    public String[] countryEconomyList;
    public String[] countryGeographyList;
    public String[] countryGovernmentList;
    public String[] countryMainList;
    public String[] countryPeopleAndSocietyList;
    public String[] countryTransportationList;
    public String[] worldCommunicationsList;
    public String[] worldEconomyList;
    public String[] worldGeographyList;
    public String[] worldGovernmentList;
    public String[] worldMainList;
    public String[] worldPeopleAndSocietyList;
    
    /**
     * Constructor in which populates string arrays to be loaded into a JList
     */
    public CIAWorldFactbook() throws FileNotFoundException
    {
        countryCommunicationsList = populateList("HeaderDocuments/COUNTRY_COMMUNICATIONS.txt");
        countryEconomyList = populateList("HeaderDocuments/COUNTRY_ECONOMY.txt");
        countryGeographyList = populateList("HeaderDocuments/COUNTRY_GEOGRAPHY.txt");
        countryGovernmentList = populateList("HeaderDocuments/COUNTRY_GOVERNMENT.txt");
        countryMainList = populateList("HeaderDocuments/COUNTRY_MAIN.txt");
        countryPeopleAndSocietyList = populateList("HeaderDocuments/COUNTRY_PEOPLEANDSOCIETY.txt");
        worldCommunicationsList = populateList("HeaderDocuments/WORLD_COMMUNICATIONS.txt");
        worldEconomyList = populateList("HeaderDocuments/WORLD_ECONOMY.txt");
        worldGeographyList = populateList("HeaderDocuments/WORLD_GEOGRAPHY.txt");
        worldGovernmentList = populateList("HeaderDocuments/WORLD_GOVERNMENT.txt");
        worldMainList = populateList("HeaderDocuments/WORLD_MAIN.txt");
        worldPeopleAndSocietyList = populateList("HeaderDocuments/WORLD_PEOPLEANDSOCIETY.txt");
    }
        

    /**
     * Populates an array of strings given a country fact list
     */
    private static String[] populateList(String fileName) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File(fileName));
        ArrayList<String> list = new ArrayList<String>();
        while (scan.hasNext())
        {
            list.add(scan.nextLine().trim());
                    
        }
        
        String[] temp = new String[list.size()];
        
        for (int i = 0; i < list.size(); ++i)
        {
            temp[i] = list.get(i);
        }
        
        return temp;
    }
    
}
