package com.revature.daos;

import com.revature.models.Pokemon;
import com.revature.models.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserPokemonDAO extends JpaRepository<UserPokemon, Integer> {
    @Query("from userPokemons where user_id = :userId")
    List<Pokemon> findAllByUserId(@Param("userId")int userId);

    @Query("from pokemon p where p.id not in (select up.pokemon.id from userPokemons up where up.user.id = :userId)")
    List<Pokemon> findAllNotAcquiredByUser(@Param("userId") int userId);
}
