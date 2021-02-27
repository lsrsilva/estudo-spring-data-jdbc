package com.lsr.estudospringjdbc.repositories;

import com.lsr.estudospringjdbc.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT * FROM USER")
    List<User> testeNativeQuery();
}
