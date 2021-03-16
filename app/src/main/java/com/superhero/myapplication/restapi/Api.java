package com.superhero.myapplication.restapi;

import com.superhero.myapplication.model.Dashboard;
import com.superhero.myapplication.model.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("{id}")
    Call<Dashboard> PopulateDashboard (@Path("id") String id);

    @GET("search/{query}")
    Call<SearchResults> Search (@Path("query") String query);



}
