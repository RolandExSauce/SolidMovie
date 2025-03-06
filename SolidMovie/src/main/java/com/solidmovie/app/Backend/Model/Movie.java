package com.solidmovie.app.Backend.Model;
import com.solidmovie.app.Utils.Genre;
import java.util.List;
import java.util.Arrays;

//for exercise 2, fetch from this swagger API
//https://prog2.fh-campuswien.ac.at/swagger-ui/index.html#/movie-controller/getMovies

//Define "Model", in this case the structure of a movie, later we
//can add here entity classes in combination with mappers such as hibernate or whatever
public class Movie {

    private final String title;
    private final String description;
    private final List<Genre> genres;

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

    //override to string for readable output of toString
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genres=" + genres +
                '}';
    };
}

