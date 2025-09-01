package dev.java10x.project_movie_api.service.imp;

import dev.java10x.project_movie_api.model.Genre;
import dev.java10x.project_movie_api.model.Movie;
import dev.java10x.project_movie_api.repository.GenreRepository;
import dev.java10x.project_movie_api.repository.MovieRepository;
import dev.java10x.project_movie_api.service.IMovieService;
import dev.java10x.project_movie_api.specifications.MovieSpecifications;
import dev.java10x.project_movie_api.ws.MovieTo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService{


    @Autowired
    private final MovieRepository movieRepository;

    @Autowired
    private final GenreRepository genreRepository;

    @Override
    public List<Movie> getAll() {
       return movieRepository.findAll();
    }

    @Override
    public String saveMovie(MovieTo movieTo) {

        Long isExist = movieRepository.countMovieByMovieName(movieTo.getMovieName());

        if (isExist >= 1){
            throw new RuntimeException("Ja existe");
        }

        Genre genre = genreRepository.getGenreByName(movieTo.getGenreName());

        if (genre.toString().isEmpty()){
            throw new RuntimeException("Genero não existe");
        }
        Movie movie = new Movie();

        movie.setMovieName(movieTo.getMovieName());
        movie.setMovieDesc(movieTo.getMovieDesc());
        movie.setInsertId(movieTo.getInsertId());
        movie.setInsertDate(new Date());
        movie.setGenre(genre);

        movieRepository.save(movie);

        return "Movie criado";
    }

    @Override
    public List<Movie> findMovieByFilter(MovieTo movie) {

        Specification<Movie> movieSpecification = MovieSpecifications.findbyFilter(movie);

        return movieRepository.findAll(movieSpecification);
    }

    @Override
    public String deleteMovie(MovieTo movie) {

        List<Movie> movieDel = findMovieByFilter(movie);

        if (movieDel.size() > 1){
            throw new RuntimeException("Mais que um item");
        }

        try {
            movieRepository.delete(movieDel.get(0));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return "Deletado com sucesso";
    }

    @Override
    public String updadeMovie(MovieTo movieTo) {

        List<Movie> movie = findMovieByFilter(movieTo);

        if (movie.size() > 1){
            throw new RuntimeException("Mais que um item");
        }

        Genre genre = genreRepository.getGenreByName(movieTo.getGenreName());

        if (genre.toString().isEmpty()){
            throw new RuntimeException("Genero não existe");
        }

        movie.get(0).setMovieName(movieTo.getMovieName());
        movie.get(0).setMovieDesc(movieTo.getMovieDesc());
        movie.get(0).setInsertId(movieTo.getInsertId());
        movie.get(0).setInsertDate(new Date());
        movie.get(0).setGenre(genre);

        movieRepository.save(movie.get(0));


        return "";
    }


}
