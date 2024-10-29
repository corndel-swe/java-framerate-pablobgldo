package com.corndel.framerate.controllers;

import com.corndel.framerate.models.Movie;
import com.corndel.framerate.models.Review;
import com.corndel.framerate.repositories.MovieRepository;
import com.corndel.framerate.repositories.ReviewRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MovieController {

    public static void getAll(Context ctx) throws SQLException {
        List<Movie> movies = MovieRepository.findAll();
        ctx.render("index.html", Map.of("movies", movies));
    }


    public static void getById(Context ctx) throws SQLException {
        int id = Integer.parseInt(ctx.pathParam("movieId"));
        Movie movie = MovieRepository.findById(id);
        List<Review> reviews = ReviewRepository.findByMovie(id);
        ctx.render("movie.html", Map.of("movie", movie, "reviews", reviews)).status(200);
    }
}
