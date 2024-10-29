package com.corndel.framerate.repositories;

import com.corndel.framerate.DB;
import com.corndel.framerate.models.Review;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
  public static List<Review> findByMovie(int movieId) throws SQLException {
    var query = "SELECT * FROM REVIEWS WHERE movieId = ?";

    try (var con = DB.getConnection();
        var stmt = con.prepareStatement(query)) {
      stmt.setInt(1, movieId);
      try (var rs = stmt.executeQuery()) {
        var reviews = new ArrayList<Review>();
        while (rs.next()) {
          var id = rs.getInt("id");
          var createdAt = rs.getLong("createdAt");
          var content = rs.getString("content");
          var rating = rs.getInt("rating");
          reviews.add(new Review(id, movieId, createdAt, content, rating));
        }
        return reviews;
      }
    }
  }

  public static Review addReview(int movieId, String content, int rating) throws SQLException {
    var query = "INSERT INTO REVIEWS (movieId, content, rating) VALUES (?, ?, ?) RETURNING *";

    try (var con = DB.getConnection();
         var stmt = con.prepareStatement(query)) {

      stmt.setInt(1, movieId);
      stmt.setString(2, content);
      stmt.setInt(3, rating);

      try (var rs = stmt.executeQuery()) {
        if (rs.next()) {
          int id = rs.getInt("id");
          long createdAt = rs.getLong("createdAt");
          return new Review(id, movieId, createdAt, content, rating);
        } else {
          throw new SQLException("Inserting review failed");
        }
      }
    }
  }


}
