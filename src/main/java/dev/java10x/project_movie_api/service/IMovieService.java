package dev.java10x.project_movie_api.service;

import dev.java10x.project_movie_api.model.Movie;
import dev.java10x.project_movie_api.ws.MovieTo;

import java.util.List;

public interface IMovieService  {

    public List<Movie> getAll();

    public String saveMovie(MovieTo movie);

    public List<Movie> findMovieByFilter(MovieTo movie);

    public String deleteMovie(MovieTo movie);

    public String updadeMovie(MovieTo movie);
}
