package com.solidmovie.app.Backend.Repo;
import com.solidmovie.app.Backend.Model.Movie;
import com.solidmovie.app.Utils.Genre;
import java.util.List;


//movie repo, will act like a database for now
public class MovieRepo {

    //instantiate list of movies
    private static final List<Movie> MOVIES = List.of(
            new Movie("Inception", "A thief who enters the dreams of others to steal their secrets.", Genre.SCIENCE_FICTION, Genre.ACTION, Genre.THRILLER),
            new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control to his reluctant son.", Genre.CRIME, Genre.DRAMA),
            new Movie("Schindler's List", "A German industrialist saves Jewish refugees during the Holocaust.", Genre.BIOGRAPHY, Genre.DRAMA, Genre.HISTORY),
            new Movie("The Dark Knight", "Batman battles the Joker, a criminal mastermind spreading chaos in Gotham.", Genre.ACTION, Genre.CRIME, Genre.DRAMA),
            new Movie("Forrest Gump", "A man with a low IQ unintentionally influences historical events.", Genre.DRAMA, Genre.ROMANCE),
            new Movie("Interstellar", "A team of explorers travels through a wormhole in space.", Genre.SCIENCE_FICTION, Genre.DRAMA, Genre.ADVENTURE),
            new Movie("Parasite", "A poor family schemes to become employed by a wealthy family.", Genre.DRAMA, Genre.THRILLER),
            new Movie("Gladiator", "A betrayed Roman general seeks revenge in the Colosseum.", Genre.ACTION, Genre.DRAMA, Genre.HISTORY),
            new Movie("Titanic", "A romance set aboard the doomed RMS Titanic.", Genre.DRAMA, Genre.ROMANCE),
            new Movie("The Lion King", "A young lion prince flees his kingdom, only to return as a leader.", Genre.ANIMATION, Genre.DRAMA, Genre.ADVENTURE),
            new Movie("Pulp Fiction", "Multiple stories interwoven in a world of crime and humor.", Genre.CRIME, Genre.DRAMA, Genre.COMEDY),
            new Movie("The Matrix", "A hacker discovers the shocking reality of his world.", Genre.SCIENCE_FICTION, Genre.ACTION),
            new Movie("Avengers: Endgame", "The Avengers assemble for one final stand against Thanos.", Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE),
            new Movie("Jurassic Park", "Scientists create cloned dinosaurs, leading to chaos.", Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.THRILLER),
            new Movie("The Shawshank Redemption", "A wrongly convicted man forms a deep friendship in prison.", Genre.DRAMA),
            new Movie("Fight Club", "An insomniac office worker forms an underground fight club.", Genre.DRAMA, Genre.THRILLER),
            new Movie("The Silence of the Lambs", "An FBI agent consults a cannibal to catch a serial killer.", Genre.THRILLER, Genre.CRIME, Genre.HORROR),
            new Movie("Star Wars: A New Hope", "A young farm boy joins rebels to fight the Empire.", Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.ACTION),
            new Movie("The Lord of the Rings: The Fellowship of the Ring", "A hobbit embarks on a journey to destroy an evil ring.", Genre.FANTASY, Genre.ADVENTURE, Genre.DRAMA),
            new Movie("Spider-Man: No Way Home", "Peter Parker faces multiverse challenges.", Genre.ACTION, Genre.SCIENCE_FICTION, Genre.ADVENTURE),
            new Movie("The Grand Budapest Hotel", "A hotel concierge becomes entangled in a murder mystery.", Genre.COMEDY, Genre.DRAMA),
            new Movie("Whiplash", "A young drummer seeks greatness under a ruthless instructor.", Genre.DRAMA, Genre.MUSICAL),
            new Movie("Black Panther", "A prince must defend his kingdom from a powerful rival.", Genre.ACTION, Genre.SCIENCE_FICTION, Genre.DRAMA),
            new Movie("Bohemian Rhapsody", "The life of Queen's lead singer Freddie Mercury.", Genre.BIOGRAPHY, Genre.DRAMA, Genre.MUSICAL),
            new Movie("Django Unchained", "A freed slave seeks revenge and justice in the Old West.", Genre.WESTERN, Genre.ACTION, Genre.DRAMA),
            new Movie("Coco", "A young boy discovers the world of the dead through music.", Genre.ANIMATION, Genre.FAMILY, Genre.MUSICAL),
            new Movie("Mad Max: Fury Road", "Survivors fight for survival in a post-apocalyptic desert.", Genre.ACTION, Genre.SCIENCE_FICTION),
            new Movie("A Beautiful Mind", "A brilliant but troubled mathematician struggles with schizophrenia.", Genre.BIOGRAPHY, Genre.DRAMA),
            new Movie("The Revenant", "A frontiersman seeks vengeance after being left for dead.", Genre.ADVENTURE, Genre.DRAMA, Genre.WESTERN),
            new Movie("La La Land", "A jazz musician and an actress fall in love while pursuing dreams.", Genre.ROMANCE, Genre.MUSICAL, Genre.DRAMA),
            new Movie("Inside Out", "A young girl's emotions guide her through life changes.", Genre.ANIMATION, Genre.FAMILY, Genre.DRAMA),
            new Movie("Toy Story", "Toys come to life when their owner is not around.", Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE),
            new Movie("The Irishman", "An old hitman recalls his life in the mob.", Genre.CRIME, Genre.DRAMA),
            new Movie("Saving Private Ryan", "A mission to rescue a soldier in World War II.", Genre.WAR, Genre.DRAMA, Genre.ACTION),
            new Movie("The Green Mile", "A death row officer meets a mysterious prisoner with a gift.", Genre.DRAMA, Genre.FANTASY),
            new Movie("Shutter Island", "A detective investigates a missing patient on a mysterious island.", Genre.THRILLER, Genre.MYSTERY, Genre.DRAMA),
            new Movie("Casablanca", "A nightclub owner must choose between love and duty.", Genre.ROMANCE, Genre.DRAMA, Genre.WAR)
    );

    /**jpa methods can be later implemented here *************************************************************************************************************************/
    //return the list of movies
    public List<Movie> getAllMovies() { return MOVIES; }
}

