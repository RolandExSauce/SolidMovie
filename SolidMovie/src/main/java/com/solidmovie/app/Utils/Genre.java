package com.solidmovie.app.Utils;


// Enum to hold the genres
public enum Genre {
    NONE("Filter by Genre"),
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    DOCUMENTARY("Documentary"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    SPORT("Sport"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private final String displayName;

    //constructor to use for user-friendly name display
    Genre(String displayName) {
        this.displayName = displayName;
    };

    //override so that when accessing Genre.DRAMA or FAMILY, etc. returns the displayName
    @Override
    public String toString() {
        return displayName;
    };
}
