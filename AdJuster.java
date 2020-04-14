/*
 Author: Imraan Iqbal
 Date: May 22, 2019
 Email: imraan1901@gmail.com
*/

import java.io.*;
import java.util.*;
import java.net.*;

public class AdJuster {

    public static void main(String[] args) throws FileNotFoundException {

        // Create the list to hold the Campaigns
        ArrayList<Campaigns> campaignList = new ArrayList<Campaigns>();

        try {
            // Open website 1
            URL url1 = new URL("http://homework.ad-juster.com/api/campaigns");
            HttpURLConnection con1 = (HttpURLConnection) url1.openConnection();
            con1.setRequestMethod("GET");
            con1.setRequestProperty("Accept", "application/json");
            InputStreamReader in = new InputStreamReader(con1.getInputStream());
            BufferedReader br = new BufferedReader(in);

            // Go through the data and insert into the list
            buildCampaigns(campaignList, br);

            // Disconnect the url connection
            con1.disconnect();

        } catch (Exception e) {
            // Catch exception
            System.out.println("Exception caught for first url " + e);
        }


        try {
            // Open website 2
            URL url2 = new URL("http://homework.ad-juster.com/api/creatives");
            HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
            con2.setRequestMethod("GET");
            con2.setRequestProperty("Accept", "application/json");
            InputStreamReader in = new InputStreamReader(con2.getInputStream());
            BufferedReader br = new BufferedReader(in);

            // Go through the data and modify the list
            buildCreatives(campaignList, br);

            // Disconnect the url connection
            con2.disconnect();

        } catch (Exception e) {

            // Catch exception
            System.out.println("Exception caught for second url " + e);
        }

        // Print the data in the desired format to console
        printData(campaignList, false);

        // Print the data in the desired format to the output file
        PrintStream fileOut = new PrintStream("./output.txt");
        System.setOut(fileOut);
        printData(campaignList);
    }

    // Print the data
    private static void printData(ArrayList<Campaigns> campaignList) {

        for(int i = 0; i < campaignList.size(); i++) {

            // Print fields regarding the campaign
            System.out.println( "Advertiser: " + campaignList.get(i).getAdvertiser() );
            System.out.println( "Start Date: " + campaignList.get(i).getStartDate() );
            System.out.println( "End Date: " + campaignList.get(i).getEndDate() );
            System.out.println( "Cpm: " + campaignList.get(i).getCpm() );
            // We can add more data output here if wanted

            int sumOfViews = 0;
            int sumOfImpressions = 0;
            int sumOfClicks = 0;
            ArrayList<Creatives> creativesList = campaignList.get(i).getCreativesList();

            // campaign has creatives
            if(creativesList != null) {

                for( int j = 0; j < creativesList.size(); j++ ) {

                    sumOfImpressions += Integer.parseInt(creativesList.get(j).getImpressions());
                    sumOfClicks += Integer.parseInt(creativesList.get(j).getClicks());
                    sumOfViews += Integer.parseInt(creativesList.get(j).getViews());
                    // We can add more data collection here if wanted
                }

                // Print fields regarding the creatives
                System.out.println( "Total Number of Views: " + sumOfViews );
                System.out.println( "Total Number of Clicks: " + sumOfClicks );
                System.out.println( "Total Number of Impressions: " + sumOfImpressions + "\n" );
                // We can add more data output here if wanted

            }
            // If Campaigns has no creatives
            else {

                System.out.println( "" );
            }
        }
    }

    // Build the campaigns list from the first url. I assumed all data will be formatted in this way.
    private static void buildCampaigns(ArrayList<Campaigns> campaignList, BufferedReader br) throws IOException {

        Campaigns campaigns = null;
        // output holds the entire string line
        String output;
        // index holds the place of : in the string
        int index;
        // temp holds a substring
        String temp;

        while ((output = br.readLine()) != null) {

            // New Campaign object
            if (output.contains("{")) {

                campaigns = new Campaigns();
                continue;

            }
            // End of Campaign object
            else if (output.contains("}")) {
                campaignList.add(campaigns);
                continue;
            }
            // End of data string
            else if (output.contains("[") || output.contains("]")) {

                continue;
            }

            // Find the index after :
            index = output.indexOf(':');

            // Assign the string from : on into temp
            temp = output.substring(index);

            // Remove unwanted characters and replace with a blank
            temp = temp.replaceAll("[\\:\\ \\,\\ \"]", "");

            // Add temp to correct field in campaigns
            if (output.contains("advertiser")) {

                campaigns.setAdvertiser(temp);

            } else if (output.contains("cpm")) {

                campaigns.setCpm(temp);

            } else if (output.contains("endDate")) {

                campaigns.setEndDate(temp);

            } else if (output.contains("id")) {

                campaigns.setId(temp);

            } else if (output.contains("name")) {

                campaigns.setName(temp);

            } else if (output.contains("startDate")) {

                campaigns.setStartDate(temp);

            }
        }
    }

    // Build the creatives list from the second url. I assumed all data will be formatted in this way.
    private static void buildCreatives(ArrayList<Campaigns> campaignList, BufferedReader br) throws IOException {

        Creatives creatives = null;
        // output holds the entire string line
        String output;
        // index holds the place of : in the string
        int index;
        // temp holds a substring
        String temp;

        while ((output = br.readLine()) != null) {

            // New Creatives object
            if (output.contains("{")) {

                creatives = new Creatives();
                continue;

            }
            // End of Creatives object
            else if (output.contains("}")) {

                // Find parent of creative in campaigns list
                for(int i=0; i < campaignList.size(); i++) {

                    if(campaignList.get(i).getId().equals( creatives.getParentId() )){

                        // If the creatives list is empty make it
                        if( campaignList.get(i).getCreativesList() == null ) {

                            campaignList.get(i).setCreativesList(new ArrayList<Creatives>());

                            // Add creatives to parents creatives list
                            campaignList.get(i).addToCreativesList(creatives);
                        }
                        else {

                            // Add creatives to parents creatives list
                            campaignList.get(i).addToCreativesList(creatives);
                        }
                        break;
                    }

                }
                continue;

            }
            // End of data string
            else if (output.contains("[") || output.contains("]")) {

                continue;
            }

            // Find the index after :
            index = output.indexOf(':');

            // Assign the string from : on into temp
            temp = output.substring(index);

            // Remove unwanted characters and replace with a blank
            temp = temp.replaceAll("[\\:\\ \\,\\ \"]", "");

            // Add temp to correct field in creatives
            if (output.contains("clicks")) {

                creatives.setClicks(temp);

            } else if (output.contains("conversations")) {

                creatives.setConversions(temp);

            } else if (output.contains("id")) {

                creatives.setId(temp);

            } else if (output.contains("impressions")) {

                creatives.setImpressions(temp);

            } else if (output.contains("name")) {

                creatives.setName(temp);

            } else if (output.contains("parentId")) {

                creatives.setParentId(temp);

            } else if (output.contains("views")) {

                creatives.setViews(temp);

            }
        }
    }
}
