package com.corndel.framerate.models;

public class Review {
  public int id;
  public int movieId;
  public long createdAt;
  public String content;
  public int rating;

  public Review(int id, int movieId, long createdAt, String content, int rating) {
    this.id = id;
    this.movieId = movieId;
    this.createdAt = createdAt;
    this.content = content;
    this.rating = rating;
  }
}
