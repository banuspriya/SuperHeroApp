package com.superhero.myapplication.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SearchResults {
    public String response;
    @SerializedName("results-for")
    public String query;
    public List<Dashboard> results;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Dashboard> getResults() {
        return results;
    }

    public void setResults(List<Dashboard> results) {
        this.results = results;
    }
}
