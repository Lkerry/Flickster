package com.example.labadmin.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by labadmin on 7/20/17.
 */

public class Movie implements Serializable{
    String posterpath;
    String backdroppath;
    String originalTitle;
    String overview;

    public float getScore() {
        return score;
    }

    float score;

    public String getPosterPath(){
        return String.format ("https://image.tmdb.org/t/p/w342/%s" , posterpath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmb.org/t/p/w342/%s", backdroppath);

    }

    public String getOriginalTitle() { return originalTitle; }

    public String getOverview() { return overview; }



    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterpath = jsonObject.getString("poster_path");
        this.backdroppath = jsonObject.getString("backdrop_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.score = (float) jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> Results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            Movie m = null;
            try {
                m = new Movie(array.getJSONObject(i));
            }catch(JSONException e) {
                e.printStackTrace();
            }

            Results.add(m);
        }
        return Results;

    }

}
