package com.lsr.estudospringjdbc.repositories;

import com.lsr.estudospringjdbc.entities.ListaUser;
import com.lsr.estudospringjdbc.entities.User;
import com.lsr.estudospringjdbc.repositories.filter.FilterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaUserRepository extends PagingAndSortingRepository<ListaUser, Long>, FilterRepository<ListaUser> {
    default Page<ListaUser> search(String termToSearch, Integer page, Integer size, String sortProperty, String sortDirection) {
        return search(termToSearch, page, size, sortProperty, sortDirection, ListaUser.class);
    }
}
