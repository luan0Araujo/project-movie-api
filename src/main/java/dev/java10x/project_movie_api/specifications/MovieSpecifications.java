package dev.java10x.project_movie_api.specifications;

import dev.java10x.project_movie_api.model.Genre;
import dev.java10x.project_movie_api.model.Movie;
import dev.java10x.project_movie_api.ws.MovieTo;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MovieSpecifications {
    public static Specification<Movie> findbyFilter(MovieTo movie) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            //JOIN
            // Sintaxe explícita:
            // Join<Pedido, Cliente> clienteJoin = root.join("cliente", JoinType.INNER);
            // Sintaxe implícita:
            Join<Movie, Genre> genreJoin = root.join("genre");

            if (movie.getMovieName() != null && !movie.getMovieName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("movieName")), "%" + movie.getMovieName().toLowerCase() + "%"));
            }

            if (movie.getMovieName() != null && !movie.getMovieName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("movieName")), "%" + movie.getMovieName().toLowerCase() + "%"));
            }

            if (movie.getMovieDesc() != null && !movie.getMovieDesc().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("movieDesc"), movie.getMovieDesc()));
            }

            if (movie.getGenreName() != null && !movie.getGenreName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(genreJoin.get("name"), movie.getGenreName()));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
