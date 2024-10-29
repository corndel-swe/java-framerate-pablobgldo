package com.corndel.framerate;

import com.corndel.framerate.controllers.MovieController;
import com.corndel.framerate.controllers.ReviewController;
import com.corndel.framerate.repositories.MovieRepository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;

public class App {
  public static void main(String[] args) {
    var javalin = createApp();
    javalin.start(8081);
  }

  public static Javalin createApp() {
    var app = Javalin.create(
        config -> {
          config.staticFiles.add("/public", Location.CLASSPATH);

          var resolver = new ClassLoaderTemplateResolver();
          resolver.setPrefix("/templates/");
          resolver.setSuffix(".html");
          resolver.setTemplateMode("HTML");

          var engine = new TemplateEngine();
          engine.setTemplateResolver(resolver);

          config.fileRenderer(new JavalinThymeleaf(engine));
        });


      app
          .get("/", MovieController::getAll)
          .get("/movie/{movieId}", MovieController::getById)
          .get("/review/{movieId}", ReviewController::getForm)
          .post("/review/{movieId}", ReviewController::createReview);
    return app;
  }
}
