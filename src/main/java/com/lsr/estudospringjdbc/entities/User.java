package com.lsr.estudospringjdbc.entities;

import com.lsr.estudospringjdbc.DefaultSortProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("USER")
public class User {
    @Id
    @Column("ID")
    private Long id;

    @Column("NOME")
    @DefaultSortProperty
    private String nome;

    @MappedCollection(idColumn = "ID_USER", keyColumn = "ID")
    private List<ListaUser> listaUserList;

    public List<ListaUser> getListaUserList() {
        return listaUserList;
    }

    public void ListListaUserList(List<ListaUser> listaUserList) {
        this.listaUserList = listaUserList;
    }

    public Long getId() {
        return id;
    }

    public void ListId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void ListNome(String nome) {
        this.nome = nome;
    }
}
