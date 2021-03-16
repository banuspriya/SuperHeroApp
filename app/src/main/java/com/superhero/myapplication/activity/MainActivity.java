package com.superhero.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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


public class MainActivity extends AppCompatActivity {

     private static final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
     RecyclerView recyclerView;
     SuperHeroAdapter superHeroAdapter;
     List<Dashboard> superHeros;
     private int mFilterType =0;
     private int mPreviousFilterType =0;
     String filterQuery;
     ProgressBar progress;
     private boolean isconnected;
     private LinearLayout noInternetLAyout;
     private BroadcastReceiver conectivityReceiver;
    private int mSortType = 0;
    private int mPreviousSort = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        noInternetLAyout=findViewById(R.id.NoInternet);
        registerReceivers();
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));
        progress.setVisibility(View.VISIBLE);
        checkConnectivity();
    }

    //receiver for connectivity change
    private void registerReceivers() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION);
        this.conectivityReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent ==null || intent.getAction() ==null)
                {
                    return ;
                }
                else
                {
                checkConnectivity();
                }
            }
        } ;
        this.registerReceiver(this.conectivityReceiver , filter);
    }

    //function call to check the connection state when broadcast received
    private void checkConnectivity() {

        isconnected = NetworkUtility.getConnectivity(this);
        if(isconnected)
        {
            //dashboard would be populated
            noInternetLAyout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            hitAPi();
        }

        else
        {
            //No internet screen would be seen in case of no internet
            progress.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            noInternetLAyout.setVisibility(View.VISIBLE);
        }
    }

    //Api call to populate dashboard intially
    private void hitAPi() {

        Call<SearchResults> dashboard = RetrofitClient.getInterface().Search("ja");
        dashboard.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.body().getResponse().equalsIgnoreCase("success"))
                {
                    //populate arraylist and set adapater to recycler view
                    superHeros =new ArrayList<>(response.body().getResults());
                    Collections.sort(superHeros,Dashboard.ASCENDING);
                    superHeroAdapter = new SuperHeroAdapter(superHeros,getApplicationContext());
                    recyclerView.setAdapter(superHeroAdapter);
                    progress.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.no_character_found , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();
        if(id == R.id.action_search)
        {
            //intent to begin Search Activity
            Intent intent = new Intent( MainActivity.this, SearchActivity.class);
            startActivity(intent);

        }

        if(id== R.id.action_sort)
        {
            //sort dialog displayed
            showSortDialog();
        }

        if(id == R.id.action_filter)
        {
            //foilter dialog
            showFilter();
        }

        return super.onOptionsItemSelected(item);
    }

    //show filter dilaog and filter the dashboard
    private void showFilter() {
        final String[] options = {"None" , "Marvel Comics" ,"Star Trek" , "Male" , "Female"};
        final int[] position = {-1};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Filter By");
        builder.setCancelable(false);
        builder.setNegativeButton("cancel" , (dialog, which) -> dialog.dismiss());
        builder.setSingleChoiceItems(options , mFilterType, (dialog, which)-> position[0]=which);
        builder.setPositiveButton("ok", (dialog,which)->{
            switch(position[0])
            {
                case 0:
                    filterQuery=options[0];
                    break;
                case 1 :
                    filterQuery=options[1];
                    break;
                case 2 :
                    filterQuery=options[2];
                    break;
                case 3 :
                    filterQuery=options[3];
                    break;
                case 4 :
                    filterQuery=options[4];
                    break;

                default:
                    filterQuery=options[mPreviousFilterType];
                    break;
            }
            if(position[0] == -1)
            {mFilterType = mPreviousFilterType;}
            else{
                mFilterType = position[0];
            }

            if(mPreviousFilterType != mFilterType)
            {
                superHeroAdapter.getFilter().filter(filterQuery);
            }
            mPreviousFilterType = mFilterType;
        });
        builder.create().show();
    }


    //sort the dashboard based on selection
    private void showSortDialog() {
        String[] options = {"Ascending" , "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort By");
        final int[] position = {-1};
        builder.setCancelable(false);
        builder.setNegativeButton("cancel" , (dialog, which) -> dialog.dismiss());
        builder.setIcon(R.drawable.ic_action_sort_dialog);
        builder.setSingleChoiceItems(options , mSortType, (dialog, which)-> position[0]=which);
        builder.setPositiveButton("ok", (dialog,which)->{
            switch(position[0])
            {
                case 0:
                    Collections.sort(superHeros , Dashboard.ASCENDING);
                    break;
                case 1:
                    Collections.sort(superHeros, Dashboard.DESCENDING);
                    break;

            }
            if(position[0] == -1)
            {mSortType = mPreviousSort;}
            else{
                mSortType = position[0];
            }
            if(mPreviousSort != mSortType)
            {
                superHeroAdapter = new SuperHeroAdapter(superHeros,getApplicationContext());
                recyclerView.setAdapter(superHeroAdapter);}
            mPreviousSort =mSortType;
        });
        builder.create().show();
    }

    //unregister receiver
    @Override
    protected void onDestroy() {
        super.onDestroy();
       try {
               this.unregisterReceiver(conectivityReceiver);
           }

       catch (IllegalArgumentException e)
       {
           e.printStackTrace();
       }

    }
}