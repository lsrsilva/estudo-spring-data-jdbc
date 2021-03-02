package com.lsr.estudospringjdbc.repositories.filter;

import org.springframework.data.domain.Page;

public interface FilterRepository<T> {
    Page<T> search(String termToSearch, Integer page, Integer size, String sortProperty, String sortDirection, Class<T> clazzType);
}
