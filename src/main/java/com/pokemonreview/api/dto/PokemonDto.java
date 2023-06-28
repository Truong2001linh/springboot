package com.pokemonreview.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
public class PokemonDto {
    private  Integer id;
    private String name ;
    private String type;

}
