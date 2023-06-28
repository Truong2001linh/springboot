package com.pokemonreview.api.Repositorys;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByPokemonId(int id);
}
