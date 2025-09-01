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
import java.util.Optional;

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
    public String deleteMovie(MovieTo movieTo) {

        Optional<Movie> movieDel = movieRepository.findById(movieTo.getMovieId());

        if (movieDel.isEmpty()){
            throw new RuntimeException("Movie não existe");
        }

        try {
            movieRepository.delete(movieDel.get());
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }

        return "Deletado com sucesso";
    }

    @Override
    public String updadeMovie(MovieTo movieTo) {

        Optional<Movie> movie = movieRepository.findById(movieTo.getMovieId());

        if (movie.isEmpty()){
            throw new RuntimeException("Movie não existe");
        }

        Genre genre = genreRepository.getGenreByName(movieTo.getGenreName());

        if (genre.toString().isEmpty()){
            throw new RuntimeException("Genero não existe");
        }

        movie.get().setMovieName(movieTo.getMovieName());
        movie.get().setMovieDesc(movieTo.getMovieDesc());
        movie.get().setInsertId(movieTo.getInsertId());
        movie.get().setInsertDate(new Date());
        movie.get().setGenre(genre);

        movieRepository.save(movie.get());

        return "Movie Atualizado";
    }


}
