package com.example.labadmin.flickster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {
    TextView titre;
    ImageView imagelandscape;
    ImageView imageportrait;
    RatingBar score;
    TextView synopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        com.example.labadmin.flickster.models.Movie movie = (com.example.labadmin.flickster.models.Movie) getIntent().getSerializableExtra("movie");

        titre=(TextView)findViewById(R.id.textView2);
        synopsis=(TextView)findViewById(R.id.synopsis);
        imagelandscape=(ImageView)findViewById(R.id.image1);
        score=(RatingBar)findViewById(R.id.ratingBar);

        score.setRating(movie.getScore());
        titre.setText(movie.getOriginalTitle());

        synopsis.setText(movie.getOverview());

        String im;
        im = movie.getPosterPath().toString();

        Picasso.with(getApplicationContext()).load(im).placeholder(R.drawable.placeholder)
                .transform(new RoundedCornersTransformation(8, 8)).into(imagelandscape);

    }
}
