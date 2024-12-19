package com.revature.daos;

import com.revature.models.Pokemon;
import com.revature.models.UserShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserShopDAO extends JpaRepository<UserShop, Integer> {
    @Query("from UserShop where user.id = :userId")
    Optional<UserShop> findByUserId(@Param("userId") int userId);
}
