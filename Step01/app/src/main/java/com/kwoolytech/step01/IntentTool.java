package com.kwoolytech.step01;

public class IntentTool {
    public final static int GetCompanyResourceId(String company) {
        int resourceId = 0;

        switch(company) {
            case "Dasan Networks":
                resourceId = R.drawable.dasannetworks;
                break;
            case "SK Telecom":
                resourceId = R.drawable.sktelecom;
                break;
            case "GE Appliances":
                resourceId = R.drawable.geappliances;
                break;
            default:
                resourceId = R.drawable.auckland;
                break;
        }

        return resourceId;
    }

    public final static String GetCompanyHomepageURL(String company) {
        String url;

        switch (company) {
            case "Dasan Networks":
                url = "http://www.dasannetworks.com";
                break;
            case "SK Telecom":
                url = "http://www.sktelecom.com";
                break;
            case "GE Appliances":
                url = "http://www.geappliances.com";
                break;
            default:
                url = "http://www.aucland.ac.nz";
                break;
        }

        return url;
    }
}
