package com.corndel.framerate.controllers;

import com.corndel.framerate.repositories.ReviewRepository;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.Map;

public class ReviewController {

    public static void getForm(Context ctx) {
        int movieId = Integer.parseInt(ctx.pathParam("movieId"));
        ctx.render("review.html", Map.of("movieId", movieId));
    }
    public static void createReview(Context ctx) throws SQLException {
        int movieId = Integer.parseInt(ctx.pathParam("movieId"));
        String content = ctx.formParam("content");
        int rating = Integer.parseInt(ctx.formParam("rating"));
        ReviewRepository.addReview(movieId, content, rating);

    }
}
