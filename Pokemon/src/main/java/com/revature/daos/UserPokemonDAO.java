package com.revature.daos;

import com.revature.models.Pokemon;
import com.revature.models.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Repository
public interface UserPokemonDAO extends JpaRepository<UserPokemon, Integer> {
    @Query("from pokemons p join userPokemons up on up.pokemon_id = p.pokemon_id where up.userId = :userId and p.name = :name")
    Optional<Pokemon> findPokemonByName(@Param("userId") int userId, @Param("name") String name);

    @Query("from pokemons p where p.id in (select up.pokemon.id from userPokemons up where up.user_id = :userId)")
    Set<Pokemon> findAcquired(@Param("userId")int userId);

    @Query("from pokemons p where p.id not in (select up.pokemon.id from userPokemons up where up.user_id = :userId)")
    Set<Pokemon> findUnacquired(@Param("userId") int userId);

    @Query("from pokemons p where type1 = :type or type2 = :type")
    Set<Pokemon> findByType(@Param("type") String type);
}
