package com.solidmovie.app.Backend;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidmovie.app.Backend.Model.Movie;



public class Helpers {

    //convert http response to
   public static Movie[] convertResponse (String res) {
       try {
           // Create an ObjectMapper instance
           ObjectMapper objectMapper = new ObjectMapper();

           // Deserialize the JSON string into an array of Movie objects
           return objectMapper.readValue(res, Movie[].class);
       }
       catch (Exception e) {
           System.out.println("Error converting response " + e);
       }
       return null;
   }

}
