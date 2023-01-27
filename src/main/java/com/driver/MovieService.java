package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie){
        return movieRepository.addMovie(movie);
    }
    public String addDirector(Director director){
        return movieRepository.addDirector(director);
    }
    public String addMovieDirector(String mvName, String drName){
        return movieRepository.addMovieDirector(mvName,drName);
    }
    public Movie getMovie(String name){
        return movieRepository.getMovie(name);
    }

    public Director getDirector(String name){
        return movieRepository.getDirector(name);
    }

    public List<String> getMoviesName(String name){
        return movieRepository.getMoviesName(name);
    }
    public List<String> getAllMovie(){
        return movieRepository.getAllMovie();
    }
    public String deleteDirector(String name){
        return movieRepository.deleteDirector(name);
    }

    public String deleteAllDirector(){
        return movieRepository.deleteAllDirector();
    }
}
