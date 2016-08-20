package com.kwoolytech.step04;

public class CommonTool {

    public static String temperatureUnit = "C";

    public final static String WEATHERQUERYURL = "http://api.openweathermap.org/data/2.5/weather?";
    public final static String APIKEY = "&APPID=7c9bbeb8ddd8520e0d7a72bf796ff493";
    public final static String WEATHERICONURL = "http://openweathermap.org/img/w/";

    public final static int CODE_MAP_REQUEST = 1001;
    public final static int CODE_SETTINGS_REQUEST = 1101;

    public static String getCommonTemperature(int celsius) {
        int ret;

        if (temperatureUnit.equalsIgnoreCase("C")) {
            ret = celsius;
        }
        else {
            ret = (9/5) * celsius + 32;
        }

        return Integer.toString(ret);
    }
}
