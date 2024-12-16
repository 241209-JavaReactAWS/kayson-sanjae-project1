package com.revature.daos;

import com.revature.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonDao extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findByName(String name);
}
