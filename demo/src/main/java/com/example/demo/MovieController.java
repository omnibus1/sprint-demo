package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    @GetMapping("/movies")
    public List<Movie> getAll(){
        return movieRepository.getAll();
    }

    @GetMapping("/movies/{id}")
    public Movie getById(@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("/movies")
    public int addMovie(@RequestBody List<Movie>movies){
        return movieRepository.add(movies);
    }
    @PutMapping("/movies/{id}")
    public int update(@PathVariable("id") int id,@RequestBody Movie updatedMovie){
        Movie movie=movieRepository.getById(id);
        if(movie==null)return -1;

        movie.setName(updatedMovie.getName());
        movie.setRating(updatedMovie.getRating());
        movieRepository.update(movie);
        return 1;
    }

    @PatchMapping("/movies/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie){
        Movie movie=movieRepository.getById(id);
        if(movie!=null){
            if(updatedMovie.getName()!=null){
                movie.setName(updatedMovie.getName());
            }
            if(updatedMovie.getRating()>0){
                movie.setRating(updatedMovie.getRating());
            }
            movieRepository.update(movie);
            return 1;
        }
        else{
            return -1;
        }
    }

}
