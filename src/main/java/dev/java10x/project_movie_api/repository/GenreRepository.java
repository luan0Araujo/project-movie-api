package dev.java10x.project_movie_api.repository;

import dev.java10x.project_movie_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Long> {

    public Genre getGenreByName(String movieName);
}
