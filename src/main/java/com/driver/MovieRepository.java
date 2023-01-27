package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    Map<String,Movie> MV=new HashMap<>();
    Map<String,Director> DR=new HashMap<>();
    Map<String, List<String>> movieDirector=new HashMap<>();

    /// Add movie and director
    public String addMovie(Movie movie){
        MV.put(movie.getName(),movie);
        return "New Movie Successfully Added.";
    }
    public String addDirector(Director director){
        DR.put(director.getName(),director);
        return "New Director Successfully Added.";
    }

    ///Pairing movie and director
    public String addMovieDirector(String mvName, String drName){
        if(!MV.containsKey(mvName) || !DR.containsKey(drName)) return null;
        List<String> mv=movieDirector.getOrDefault(drName,new ArrayList<>());
        if(mv.contains(mvName)) return "Already Existed";
        mv.add(mvName);
        movieDirector.put(drName,mv);
        return "Movie and Director successfully paired.";
    }

    ///Get methods

    public Movie getMovie(String name){
        if(!MV.containsKey(name)) return null;
        return MV.get(name);
    }

    public Director getDirector(String name){
        if(!DR.containsKey(name)) return null;
        return DR.get(name);
    }

    public List<String> getMoviesName(String name){
        if(movieDirector.containsKey(name)) return movieDirector.get(name);
        return null;
    }
    public List<String> getAllMovie(){
        List<String> mvlist=new ArrayList<>();
        for(String movie:MV.keySet()){
            mvlist.add(movie);
        }
        return mvlist;
    }
    ///// Delete method
    public String deleteDirector(String name){
        List<String> mvname=new ArrayList<>();
        if(movieDirector.containsKey(name)){
            mvname=movieDirector.get(name);
            for(String val:mvname){
                if(MV.containsKey(val)){
                    MV.remove(val);
                }
            }
            movieDirector.remove(name);
        }
        if (DR.containsKey(name)){
            DR.remove(name);
        }
        return "Successfully Deleted";
    }

    public String deleteAllDirector(){
        for(String directorName:movieDirector.keySet()){
            List<String> Dir=new ArrayList<>();
            Dir=movieDirector.get(directorName);
            for(String name:Dir){
                if(MV.containsKey(name)) MV.remove(name);
            }
            movieDirector.remove((directorName));
        }
        for(String directorName:DR.keySet()){
            DR.remove(directorName);
        }
        return "Success";
    }


}
