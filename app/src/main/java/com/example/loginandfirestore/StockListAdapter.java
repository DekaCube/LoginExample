package com.example.loginandfirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StockListAdapter extends ArrayAdapter<Stock> {
    Context mContext;


    public StockListAdapter(Context context, ArrayList<Stock> items) {
        super(context, 0, items);

        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Stock stock = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_stock, parent, false);
        }
        // Lookup views for data population
        TextView cp = (TextView) convertView.findViewById(R.id.change_percent);
        TextView symbol = (TextView) convertView.findViewById(R.id.symbol);

        // Populate the data into the template view using the data object
        char char_indicator = stock.getChangePercent().charAt(0);

        if(char_indicator == '-'){
            cp.setTextColor(mContext.getResources().getColor(R.color.stock_red));
        }
        else{
            cp.setTextColor(mContext.getResources().getColor(R.color.stock_green));
        }

        cp.setText(stock.getChangePercent());
        symbol.setText(stock.getSymbol());

        // Return the completed view to render on screen
        return convertView;
    }


}