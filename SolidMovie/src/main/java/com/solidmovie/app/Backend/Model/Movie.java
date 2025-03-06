package com.solidmovie.app.Backend.Model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solidmovie.app.Utils.Genre;
import java.util.List;


//Define "Model", in this case the structure of a movie, later we
//can add here entity classes in combination with mappers such as hibernate or whatever

//record class
public record Movie(String id, String title, List<Genre> genres, Integer releaseYear, String description, String imgUrl,
                    Integer lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast,
                    Integer rating) {

    //for simplified tests we will use this constructor so that we dont have to create
    //a movie with all the fields from the second constructor
    public Movie (String title, String description, List<Genre> genres){
        this(
                null, title, genres,null,
                description,null,null,
                null,null,null,null
        );
    }

    @JsonCreator
    public Movie(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("genres") List<Genre> genres,
            @JsonProperty("releaseYear") Integer releaseYear,
            @JsonProperty("description") String description,
            @JsonProperty("imgUrl") String imgUrl,
            @JsonProperty("lengthInMinutes") Integer lengthInMinutes,
            @JsonProperty("directors") List<String> directors,
            @JsonProperty("writers") List<String> writers,
            @JsonProperty("mainCast") List<String> mainCast,
            @JsonProperty("rating") Integer rating
    ) {
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
    /******************************************************************************************************************/
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

