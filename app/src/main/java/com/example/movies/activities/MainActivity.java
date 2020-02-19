package com.example.movies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    private EditText editTextForSearching;
    private Button buttonForSearching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextForSearching = findViewById(R.id.editTextForSearching);
        buttonForSearching = findViewById(R.id.buttonForSearching);

    }

    public void startSearching(View view) {
        String movieName = editTextForSearching.getText().toString();

        if (movieName.isEmpty()) {
            Toast.makeText(this, "Enter the movie name", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, SearchMoviesActivity.class);
            intent.putExtra("movieName", movieName);
            startActivity(intent);
        }
    }
}
