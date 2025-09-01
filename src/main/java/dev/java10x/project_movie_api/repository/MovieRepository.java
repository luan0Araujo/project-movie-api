package dev.java10x.project_movie_api.repository;

import dev.java10x.project_movie_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    public Long countMovieByMovieName(String movieName);

//    EXEMPLO
//    @Query("SELECT p FROM Product p WHERE (:category IS NULL OR p.category = :category) AND p.price > :minPrice")
//    List<Product> findByOptionalCategoryAndMinPrice(@Param("category") String category, @Param("minPrice") double minPrice);

}
