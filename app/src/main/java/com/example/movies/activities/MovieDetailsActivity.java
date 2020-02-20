package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetailsActivity extends AppCompatActivity {
    private String imdbID;
    private ImageView detailPosterImageView;
    private TextView detailTitleTextView;
    private TextView detailYearTextView;
    private TextView detailRatedTextView;
    private TextView detailReleasedTextView;
    private TextView detailRuntimeTextView;
    private TextView detailGenreTextView;
    private TextView detailDirectorTextView;
    private TextView detailWriterTextView;
    private TextView detailActorsTextView;
    private TextView detailPlotTextView;
    private TextView detailLanguageTextView;
    private TextView detailCountryTextView;
    private TextView detailAwardsTextView;
    private TextView detailRatingTextView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        imdbID = intent.getStringExtra("imdbID");
        detailPosterImageView = findViewById(R.id.detailPosterImageView);
        detailTitleTextView = findViewById(R.id.detailTitleTextView);
        detailYearTextView = findViewById(R.id.detailYearTextView);
        detailRatedTextView = findViewById(R.id.detailRatedTextView);
        detailReleasedTextView = findViewById(R.id.detailReleasedTextView);
        detailRuntimeTextView = findViewById(R.id.detailRuntimeTextView);
        detailGenreTextView = findViewById(R.id.detailGenreTextView);
        detailDirectorTextView = findViewById(R.id.detailDirectorTextView);
        detailWriterTextView = findViewById(R.id.detailWriterTextView);
        detailActorsTextView = findViewById(R.id.detailActorsTextView);
        detailPlotTextView = findViewById(R.id.detailPlotTextView);
        detailLanguageTextView = findViewById(R.id.detailLanguageTextView);
        detailCountryTextView = findViewById(R.id.detailCountryTextView);
        detailAwardsTextView = findViewById(R.id.detailAwardsTextView);
        detailRatingTextView = findViewById(R.id.detailRatingsTextView);
        requestQueue = Volley.newRequestQueue(this);
        getMovie();
    }


    private void getMovie() {

        String url = "http://www.omdbapi.com/?i=" + imdbID + "&apikey=aaf1fb17";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    detailTitleTextView.setText("Title: " + response.getString("Title"));
                    detailYearTextView.setText("Year: " + response.getString("Year"));
                    detailRatedTextView.setText("Rated: " + response.getString("Rated"));
                    detailReleasedTextView.setText("Released: " + response.getString("Released"));
                    detailRuntimeTextView.setText("Runtime: " + response.getString("Runtime"));
                    detailGenreTextView.setText("Genre: " + response.getString("Genre"));
                    detailDirectorTextView.setText("Director: " + response.getString("Director"));
                    detailWriterTextView.setText("Writer: " + response.getString("Writer"));
                    detailActorsTextView.setText("Actors: " + response.getString("Actors"));
                    detailPlotTextView.setText("Plot: " + response.getString("Plot"));
                    detailLanguageTextView.setText("Language: " + response.getString("Language"));
                    detailCountryTextView.setText("Country: " + response.getString("Country"));
                    detailAwardsTextView.setText("Awards: " + response.getString("Awards"));
                    detailRatingTextView.setText("Rating: ");
                    JSONArray jsonArray = response.getJSONArray("Ratings");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        detailRatingTextView.append("Source: " + jsonObject.getString("Source")+". ");
                        detailRatingTextView.append("Value: " + jsonObject.getString("Value") + "\n");
                    }
                    String posterURL = response.getString("Poster");
                    Picasso.get().load(posterURL).fit().centerInside().into(detailPosterImageView);

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
