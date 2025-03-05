package com.solidmovie.app.Backend.Model;
import com.solidmovie.app.Utils.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//for exercise 2, fetch from this API
//https://prog2.fh-campuswien.ac.at/swagger-ui/index.html#/movie-controller/getMovies


//endpoint for making get request: https://prog2.fh-campuswien.ac.at/movies?genre=ACTION

//Define "Model", in this case the structure of a movie, later we
//can add here entity classes in combination with mappers such as hibernate or whatever
public class Movie {

    //unique id field
    private  String id;
    private Integer releaseYear;
    private String imgUrl;
    private Integer lengthInMinutes;
    //will store directors of that movie
    private List<String> directors;
    private List<String> writers; //store writers
    private List<String> mainCast; //store main cast
    private Integer rating;



    /***************************************************************************************/
    //TODO: later set this final again
    private String title;
    private String description;
    private List<Genre> genres; //TODO: Do I need to convert enum to string here ?

    //just for now
    public Movie (){}


    public Movie (
            String id,
            Integer releaseYear,
            String imgUrl,
            Integer lengthInMinutes,
            List<String> directors,
            List<String> writers,
            List<String> mainCast,
            Integer rating,
            String title,
            String description,
            List<Genre> genres
    ){
        this.id = id;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.genres = genres;
    };

    //just default constructor for now
    public Movie(String title, String description, Genre... genres) {
        this.title = title;
        this.description = description;
        this.genres = Arrays.asList(genres);
    };

    /*Getters *********************************************************************************************************/
    public String getTitle() { return title;}

    public String getDescription() { return description;}

    public List<Genre> getGenres() { return genres;}
    /******************************************************************************************************************/
    //override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!title.equals(movie.title)) return false;
        if (!description.equals(movie.description)) return false;
        return genres.equals(movie.genres);
    };

    //override hashcode
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + genres.hashCode();
        return result;
    };


    //
    //unique id field
//    private  String id;
//    private Integer releaseYear;
//    private String imgUrl;
//    private Integer lengthInMinutes;
//

    /*TODO: For now lets just render without list *************************************************/
    //will store directors of that movie
//    private List<String> directors;
//    private List<String> writers; //store writers
//    private List<String> mainCast; //store main cast
    /****************************************************/
//
//    private Integer rating;
//
//    private final String title;
//    private final String description;

    //TODO: same for genre here, find a way to turn json array to list ???
//    private final List<Genre> genres;





    //override to string for readable output of toString
    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", imgUrl='" + imgUrl + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                ", genres=" + genres +
                ", directors=" + directors +
                ", writers=" + writers +
                ", mainCast=" + mainCast +
                ", rating=" + rating +
                '}';
    };
}

