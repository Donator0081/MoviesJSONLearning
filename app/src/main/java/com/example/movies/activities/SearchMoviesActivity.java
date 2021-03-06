package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.example.movies.data.MovieAdapter;
import com.example.movies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchMoviesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private RequestQueue requestQueue;
    private String resultOfSearching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        movies = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        resultOfSearching = intent.getStringExtra("movieName");

        getMovies();

    }

    private void getMovies() {

        String url = "http://www.omdbapi.com/?apikey=aaf1fb17&s=" + resultOfSearching.toLowerCase();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Search");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Movie movie = new Movie();
                        movie.setTitle(jsonObject.getString("Title"));
                        movie.setYear(jsonObject.getString("Year"));
                        movie.setPosterURL(jsonObject.getString("Poster"));
                        movie.setImdbID(jsonObject.getString("imdbID"));

                        movies.add(movie);
                    }

                    movieAdapter = new MovieAdapter(getApplicationContext(), movies);
                    recyclerView.setAdapter(movieAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }
}
