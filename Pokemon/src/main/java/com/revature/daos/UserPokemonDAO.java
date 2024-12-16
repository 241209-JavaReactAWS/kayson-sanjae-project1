package com.revature.daos;

import com.revature.models.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPokemonDAO extends JpaRepository<UserPokemon, Integer> {

}
