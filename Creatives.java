/*
 Author: Imraan Iqbal
 Date: May 22, 2019
 Email: imraan1901@gmail.com
*/

public class Creatives {

    // All the fields in the creatives endpoint
    private String clicks;
    private String conversions;
    private String id;
    private String impressions;
    private String name;
    private String parentId;
    private String views;

    // clicks getter
    public String getClicks() {
        return clicks;
    }
    // clicks setter
    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    // conversations getter
    public String getConversions() {
        return conversions;
    }
    // conversations setter
    public void setConversions(String conversions) {
        this.conversions = conversions;
    }

    // id getter
    public String getId() { return id; }
    // id setter
    public void setId(String id) {
        this.id = id;
    }

    // impressions setter
    public String getImpressions() {
        return impressions;
    }
    // impressions getter
    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    // name getter
    public String getName() {
        return name;
    }
    // name setter
    public void setName(String name) {
        this.name = name;
    }

    // parentId getter
    public String getParentId() {
        return parentId;
    }
    // parentId setter
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    // views getter
    public String getViews() {
        return views;
    }
    // views setter
    public void setViews(String views) {
        this.views = views;
    }
}
