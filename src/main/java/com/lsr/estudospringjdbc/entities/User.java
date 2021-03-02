package com.lsr.estudospringjdbc.entities;

import com.lsr.estudospringjdbc.DefaultSortProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER")
public class User {
    @Id
    @Column("ID")
    private Long id;

    @Column("NOME")
    @DefaultSortProperty
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
