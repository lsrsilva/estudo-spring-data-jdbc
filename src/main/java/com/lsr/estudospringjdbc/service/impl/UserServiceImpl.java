package com.lsr.estudospringjdbc.service.impl;

import com.lsr.estudospringjdbc.DefaultSortProperty;
import com.lsr.estudospringjdbc.entities.User;
import com.lsr.estudospringjdbc.repositories.UserRepository;
import com.lsr.estudospringjdbc.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<User> findAll(Object value, Integer page, Integer size, String sortProperty, String sortDirection) {
        try {
            if (sortProperty == null || sortProperty.equals("undefined") || sortProperty.equals("null")) {
                sortProperty = "nome";
            }

            if (size == null) {
                size = 10;
            }

            if (page == null) {
                page = 0;
            }
            Sort.Direction direction = sortDirection != null && sortDirection.equalsIgnoreCase("asc") ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            PageRequest pageRequest = PageRequest.of(page, size, direction, sortProperty);
            if (value != null) {
                return repository.findAll(pageRequest);
            }
            return repository.findAll(pageRequest);
        } catch (Exception e) {
            throw e;
        }
    }



    public String getDefaultSortProperty() {
        String paramSort = "";

        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(DefaultSortProperty.class)) {
                paramSort = field.getName();
                break;
            }
        }

        return paramSort;
    }
}
