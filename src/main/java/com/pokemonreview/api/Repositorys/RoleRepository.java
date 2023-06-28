package com.pokemonreview.api.Repositorys;

import com.pokemonreview.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query(value = "SELECT * From Roles r where r.rolename like %:name% ",nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);
}
