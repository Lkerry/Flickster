package com.example.labadmin.flickster;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.labadmin.flickster.adapters.MovieArrayAdapter;
import com.example.labadmin.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;


public class MovieActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView LvItems;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.placeholder);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        progressBar =   (ProgressBar)findViewById(R.id.progressBar);
        LvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        LvItems.setAdapter(movieAdapter);
        LvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie =(Movie) LvItems.getItemAtPosition(i);
                Intent pastToDetail = new Intent(MovieActivity.this, MovieDetailsActivity.class);
                pastToDetail.putExtra("movie",movie);

                startActivity(pastToDetail); // brings up the second activity

            }
        });

        String Url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                    log.d("DEBUG", movieJsonResults.toString());
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        movieAdapter.notifyDataSetChanged();

    }
}



