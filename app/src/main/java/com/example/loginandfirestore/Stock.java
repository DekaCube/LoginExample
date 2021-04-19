package com.example.loginandfirestore;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Stock {
    private String symbol;
    private String open;
    private String high;
    private String low;
    private String price;
    private String volume;
    private String latest_trading_day;
    private String previous_close;
    private String change;
    private String change_percent;
    private Date timestamp;
    public static String UP_DELTA = "\u0394";
    public static String DOWN_DELTA = "\u2207";

    Stock(JSONObject o) throws JSONException {
        timestamp = new Date();
        JSONObject stock = o.getJSONObject("Global Quote");
        symbol = stock.getString("01. symbol");
        open = stock.getString("02. open");
        high = stock.getString("03. high");
        low = stock.getString("04. low");
        price = stock.getString("05. price");
        volume = stock.getString("06. volume");
        change = stock.getString("09. change");
        change_percent = stock.getString("10. change percent");


    }

    public long getTimeStamp(){
        return timestamp.getTime() / 1000;
    }

    @Override
    public String toString(){
        return symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public String getChangePercent(){
        return change_percent;
    }


}
