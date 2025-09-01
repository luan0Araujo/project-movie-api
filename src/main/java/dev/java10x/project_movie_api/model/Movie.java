package dev.java10x.project_movie_api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "TB_MOVIE")
public class Movie {

    @Id
    @SequenceGenerator(
            name = "movie_seq_generator",
            sequenceName = "SEQ_MOVIE_ID",allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // 4. Estratégia é SEQUENCE
            generator = "movie_seq_generator"     // 5. Referencia o gerador definido acima pelo seu nome lógico
    )
    private Long movieId;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @Column(name = "MOVIE_DESC")
    private String movieDesc;

    @Column(name = "IMG")
    private String img;

    @Column(name = "INSERT_ID")
    private String insertId;

    @Column(name = "INSERT_DATE")
    private Date insertDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GENRE_ID", nullable = false)
    private Genre genre;
}
