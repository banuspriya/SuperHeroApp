package com.superhero.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.superhero.myapplication.R;
import com.superhero.myapplication.adapter.SuperHeroAdapter;
import com.superhero.myapplication.model.Dashboard;
import com.superhero.myapplication.model.SearchResults;
import com.superhero.myapplication.restapi.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    RecyclerView recyclerView_search;
    SuperHeroAdapter superHeroAdapter_search;
    List<Dashboard> superHeros_search;
    String query ="";
    EditText search_query;
    private boolean isconnected;
    private LinearLayout noInternetLAyout;
    private BroadcastReceiver conectivityReceiver_search;
    private ProgressBar progress;

    //search activity : to search the super hero of users choice
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress=findViewById(R.id.progressBar);
        recyclerView_search=findViewById(R.id.recyclerview);
        recyclerView_search.setHasFixedSize(true);
        noInternetLAyout=findViewById(R.id.NoInternet);
        search_query=findViewById(R.id.search_view);
        search_query.setVisibility(View.VISIBLE);
        registerReceivers_search();
        recyclerView_search.setLayoutManager(new GridLayoutManager(this , 2));
        checkConnectivity();
        search_query.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                    query = search_query.getText().toString();
                    if(query!= null)
                    {
                       //api call after the query string is available
                        progress.setVisibility(View.VISIBLE);
                        hitApiSearch();
                        return true;
                    }
                }

                return false;
            }
        });
    }

    //receiver for connectivity check
    private void registerReceivers_search() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        this.conectivityReceiver_search= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent ==null || intent.getAction() ==null)
                {return ;}
                checkConnectivity();
            }
        } ;
        this.registerReceiver(this.conectivityReceiver_search , filter);
    }


    //to check available connection
    private void checkConnectivity() {

        isconnected = NetworkUtility.getConnectivity(this);
        if(isconnected)
        {
            noInternetLAyout.setVisibility(View.GONE);
            recyclerView_search.setVisibility(View.VISIBLE);
        }

        else
        {
            progress.setVisibility(View.GONE);
            recyclerView_search.setVisibility(View.GONE);
            noInternetLAyout.setVisibility(View.VISIBLE);
        }
    }

    //Api call to search for the query
    private void hitApiSearch() {
        progress.setVisibility(View.VISIBLE);
        Call<SearchResults> dashboard = RetrofitClient.getInterface().Search(query);
        dashboard.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.body().getResponse().equalsIgnoreCase("success"))
                {
                    superHeros_search =new ArrayList<>(response.body().getResults());
                    Collections.sort(superHeros_search,Dashboard.ASCENDING);
                    superHeroAdapter_search = new SuperHeroAdapter(superHeros_search,getApplicationContext());
                    recyclerView_search.setAdapter(superHeroAdapter_search);
                    progress.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"character with given name not found" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Error please try again" , Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            this.unregisterReceiver(conectivityReceiver_search);
        }

        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }

    }
}
