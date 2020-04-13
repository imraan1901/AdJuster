/*
 Author: Imraan Iqbal
 Date: May 22, 2019
 Email: imraan1901@gmail.com
*/

import java.util.ArrayList;

public class Campaigns {

    // All the fields in the campaigns endpoint
    private String advertiser;
    private String cpm;
    private String endDate;
    private String id;
    private String name;
    private String startDate;
    private ArrayList<Creatives> creativesList;

    // advertiser getter
    public String getAdvertiser() {
        return advertiser;
    }
    // advertiser setter
    public void setAdvertiser(String advertiser) { this.advertiser = advertiser; }

    // cpm getter
    public String getCpm() {
        return cpm;
    }
    // cpm setter
    public void setCpm(String cpm) { this.cpm = cpm; }

    // endDate getter
    public String getEndDate() {
        return endDate;
    }
    // endDate setter
    public void setEndDate(String endDate) { this.endDate = endDate; }

    // id getter
    public String getId() { return id; }
    // id setter
    public void setId(String id1) { this.id = id1; }

    // name getter
    public String getName() {
        return name;
    }
    // name setter
    public void setName(String name) { this.name = name; }

    // startDate getter
    public String getStartDate() {
        return startDate;
    }
    // startDate setter
    public void setStartDate(String startDate) { this.startDate = startDate; }

    // creatives getter
    public ArrayList<Creatives> getCreativesList() { return creativesList; }
    // creatives setter
    public void setCreativesList(ArrayList<Creatives> creatives) {
        this.creativesList = creatives;
    }
    // add method for creativesList
    public void addToCreativesList(Creatives creatives) { creativesList.add(creatives); }
}
