package com.lsr.estudospringjdbc.service;

import com.lsr.estudospringjdbc.entities.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<User> findAll(Object value, Integer page, Integer size, String sortProperty, String sortDirection);

}
