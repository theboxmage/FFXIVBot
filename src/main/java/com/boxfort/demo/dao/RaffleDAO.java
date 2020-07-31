package com.boxfort.demo.dao;

import com.boxfort.demo.Entities.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface RaffleDAO extends JpaRepository<Raffle, Long> {
    @Query("select r FROM Raffle r WHERE r.id > :ID")
    Set<Raffle> findByRaffleIdGreaterThanX(@Param("ID") Long id);
}


