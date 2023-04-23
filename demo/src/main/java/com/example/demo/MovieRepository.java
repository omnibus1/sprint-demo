package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
        return jdbcTemplate.query("select * from movie", BeanPropertyRowMapper.newInstance(Movie.class));

    }
    public Movie getById(int id){
        return jdbcTemplate.queryForObject("select * from movie where id=?",BeanPropertyRowMapper.newInstance(Movie.class),id);
    }
    public int add(List<Movie> movies){
        movies.forEach(movie->jdbcTemplate.update("insert into movie(name,rating) values (?,?)",movie.getName(),movie.getRating()));
        return 1;

    }

    public int update(Movie movie){
        return jdbcTemplate.update("update movie set name=?,rating=? where id=?",
                movie.getName(),movie.getRating(),movie.getId());
    }



}
