package com.example.loginandfirestore.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginandfirestore.Stock;
import com.example.loginandfirestore.AlphaVantage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginandfirestore.R;
import com.example.loginandfirestore.StockListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockSearchFragment extends Fragment {

    EditText mQuery;
    ImageButton mSearch;
    ListView mList;
    ArrayList<Stock> mStocks;
    String[] mTestStocks = {"IBM","AAPL"};
    ListView mStockListView;
    StockListAdapter mStockListAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        mStocks = new ArrayList<Stock>();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AlphaVantage av = AlphaVantage.getInstance();
        mStockListView = getView().findViewById(R.id.searchView);
        Stock stonk;
        for(String s : mTestStocks){
            stonk = av.getStock(s);
            if(stonk != null){
                mStocks.add(stonk);
            }

        }
        mStockListAdapter = new StockListAdapter(getContext(),mStocks);
        mStockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Add code here to do what you want when an Item is clicked
                Toast.makeText(getContext(), mStocks.get(i).getSymbol().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mStockListView.setAdapter(mStockListAdapter);

    }

    public void onSearch(View v){
        //TODO: Validation
        AlphaVantage av = AlphaVantage.getInstance();

    }
}