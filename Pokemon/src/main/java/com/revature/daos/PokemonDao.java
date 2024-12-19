package com.revature.daos;

import com.revature.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findByName(String name);

    @Query(value = "select * from pokemons order by rand() limit 5", nativeQuery = true)
    List<Pokemon> findFiveRandom();
}
