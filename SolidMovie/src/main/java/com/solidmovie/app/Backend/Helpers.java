package com.solidmovie.app.Backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidmovie.app.Backend.Model.Movie;

public class Helpers {


   public static void convertResponse (String res) {
       //convert json response coming as string to list
       //ObjectMapper mapper = new ObjectMapper(); // use Jackson for parsing
       try {
//           JsonNode rootNode = mapper.readTree(res); // Read JSON
//           JsonNode resArray = rootNode.get("");


           // Create an ObjectMapper instance
           ObjectMapper objectMapper = new ObjectMapper();

           // Deserialize the JSON string into an array of User objects
           Movie[] movies = objectMapper.readValue(res, Movie[].class);

           // Print the details of each user
           for (Movie movie : movies) {
               System.out.println("Printing out converted response: " + movie);
           }
       }
       catch (Exception e) {
           System.out.println("Error converting response " + e);
       }

   }

}
