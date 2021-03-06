package com.example.movies.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.activities.MovieDetailsActivity;
import com.example.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie currentMovie = movies.get(position);

        holder.titleTextView.setText(currentMovie.getTitle());
        holder.yearTextView.setText(currentMovie.getYear());
        Picasso.get().load(currentMovie.getPosterURL()).fit().centerInside().into(holder.posterImageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImageView;
        TextView titleTextView;
        TextView yearTextView;


        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
                    intent.putExtra("imdbID", movies.get(getAdapterPosition()).getImdbID());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().getApplicationContext().startActivity(intent);
                }
            });

        }
    }
}
