package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rolename")
    private String rolename;

    @ManyToMany(mappedBy = "roles")
    private List<UserEntity> users = new ArrayList<>();


}
