package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("movie")
@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        return new ResponseEntity<>(movieService.addMovie(movie), HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        return new ResponseEntity<>(movieService.addDirector(director), HttpStatus.CREATED);
    }

    @PutMapping ("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
        String response=movieService.addMovieDirector(movie,director);
        if(response==null) return new ResponseEntity<>("Movie or Director not found", HttpStatus.NOT_FOUND);
        else if(response.equals("Already Existed")) return new ResponseEntity<>(response, HttpStatus.FOUND);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping ("/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Movie movie=movieService.getMovie(name);
        if(movie==null) return new ResponseEntity("Movie not found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping ("/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Director director=movieService.getDirector(name);
        if(director==null) return new ResponseEntity("Director not found",HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    @GetMapping ("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String director){
        if(movieService.getMoviesName(director)==null) return new ResponseEntity("Not movies found",HttpStatus.NOT_FOUND);
        return new ResponseEntity(movieService.getMoviesName(director),HttpStatus.NOT_FOUND);
    }

    @GetMapping ("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity(movieService.getAllMovie(),HttpStatus.FOUND);
    }

    @DeleteMapping ("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String director){
        return new ResponseEntity(movieService.deleteDirector(director),HttpStatus.ACCEPTED);
    }

    @DeleteMapping ("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return new ResponseEntity(movieService.deleteAllDirector(),HttpStatus.ACCEPTED);
    }


}
