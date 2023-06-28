package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.Repositorys.PokemonRepository;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private  PokemonRepository pokemonRepository;
    @Autowired
    /* @Autowired là một annotation trong Spring Framework được sử dụng
    để thực hiện việc tự động liên kết (autowiring) các thành phần (beans) vào các thành phần khác trong ứng dụng.
       Khi bạn đánh dấu một trường (field), một phương thức setter, hoặc một constructor với
    @Autowired, Spring sẽ tự động tìm kiếm các bean phù hợp trong context và tiêm (inject) các bean đó vào các thành phần đánh dấu.*/
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon newPokemon =pokemonRepository.save(pokemon);

        PokemonDto pokemonDto1 =new PokemonDto();
        pokemonDto1.setId(newPokemon.getId());
        pokemonDto1.setName(newPokemon.getName());
        pokemonDto1.setType(newPokemon.getType());
        return pokemonDto1;
    }

    @Override
    public PokemonResponse getAllPokemon(int pageNo, int pageSize) throws PokemonNotFoundException {
        PageRequest pageable =PageRequest.of(pageNo,pageSize);
        Page<Pokemon> listPokemon =pokemonRepository.findAll( pageable);
        List<Pokemon> listOfPokemon = listPokemon.getContent();
        List<PokemonDto> content=  listOfPokemon.stream().map(p->mapToDto(p)).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(listPokemon.getNumber());
        pokemonResponse.setPageSize(listPokemon.getSize());
        pokemonResponse.setTotalElements(listPokemon.getTotalElements());
        pokemonResponse.setTotalPages(listPokemon.getTotalPages());
        pokemonResponse.setLast(listPokemon.isLast());
        return pokemonResponse;

    }

    @Override
    public PokemonDto getPokemonById(int id) throws PokemonNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon could not be found."));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemonById(PokemonDto pokemonDto, int id) throws PokemonNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon could not update."));

        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon pokemon1 = pokemonRepository.save(pokemon);

        return mapToDto(pokemon1);
    }

    @Override
    public void deletePokemon(int id) throws PokemonNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(()->new PokemonNotFoundException("Pokemon could not delete by Id."));
        pokemonRepository.delete(pokemon);
    }

    @Override
    public List<PokemonDto> findPokemonName(String name) {
        List<Pokemon> pokemonDtos = pokemonRepository.findPokemonName(name);
        return mapToDtoList(pokemonDtos);
    }

//    @Override
//    public List<PokemonDto> jasdhfkasd(String name) {
//        return null;
//    }

//    @Override
//    public List<PokemonDto> jasdhfkasd(String name) {
//        return null;
//    }

    private List<PokemonDto> mapToDtoList(List<Pokemon> pokemonList) {
        return pokemonList.stream().map(this::mapToDto).collect(Collectors.toList());
    }
    private PokemonDto mapToDto(Pokemon pokemon){
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }
    private Pokemon mapToEntity(PokemonDto pokemonDto){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }


}
