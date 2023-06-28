package com.pokemonreview.api.dto;

import com.pokemonreview.api.models.Pokemon;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class ReviewDto {
    private Integer id;
    private String title ;
    private String content;
    private int start;
}
