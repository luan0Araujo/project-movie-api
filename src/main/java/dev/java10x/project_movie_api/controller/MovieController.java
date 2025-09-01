package dev.java10x.project_movie_api.controller;

import dev.java10x.project_movie_api.model.Movie;
import dev.java10x.project_movie_api.service.IMovieService;
import dev.java10x.project_movie_api.ws.MovieTo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final IMovieService movieService;

    @GetMapping
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @RequestMapping("/save")
    public String saveMovie (@RequestBody MovieTo movie) {
        return  movieService.saveMovie(movie);
    }

    @RequestMapping("/filter")
    public List<Movie> getMovie (@RequestBody MovieTo movie) {
        return  movieService.findMovieByFilter(movie);
    }

    @RequestMapping("/delete")
    public String deleteMovie (@RequestBody MovieTo movie) {
        return movieService.deleteMovie(movie);
    }

    @RequestMapping("/updade")
    public String updadeMovie (@RequestBody MovieTo movie) {
        return  movieService.updadeMovie(movie);
    }
}
