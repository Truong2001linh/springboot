package com.pokemonreview.api.controllers;

import com.pokemonreview.api.Repositorys.PokemonRepository;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.service.PokemonService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController // Xử lí các yêu cầu trên  http
@RequestMapping("/api/") /* @RequestMapping là một annotation trong Java Spring Boot được sử dụng để ánh xạ một phương thức hoặc một lớp Controller
 với một yêu cầu HTTP cụ thể hoặc một tập hợp các yêu cầu HTTP.*/ // Ánh xạ yêu cầu
public class PokemonController {
    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Autowired
    PokemonRepository pokemonRepository;

    @GetMapping("pokemon")
    /* GET (Lấy dữ liệu):
    Phương thức GET được sử dụng để lấy dữ liệu từ server về client.
    Khi gửi yêu cầu GET, dữ liệu yêu cầu được truyền thông qua các tham số trên URL hoặc thông qua query parameters.
    GET thường được sử dụng để truy vấn, lấy thông tin từ server mà không gây ảnh hưởng đến dữ liệu trên server.
    Dữ liệu được gửi bằng phương thức GET có thể hiển thị trực tiếp trong URL.*/
//    public List<Pokemon> getPokemons(){
//        List<Pokemon> pokemonList = pokemonRepository.findAll();
//        for (Pokemon pokemon: pokemonList) {
//            System.out.println(pokemon.toString());
//
//        }
//        return pokemonList;
//    }
    public ResponseEntity<PokemonResponse> getAllPokemon(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize)
            throws PokemonNotFoundException {
        return new ResponseEntity<>( pokemonService.getAllPokemon(pageNo,pageSize),HttpStatus.OK);
//        List<Pokemon> pokemonList = pokemonRepository.findAll();
//        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("pokemon/{id}")
    /* @PathVariable là một annotation trong Java Spring Boot được sử dụng để đánh dấu một tham
 số của phương thức trong một Controller và liên kết giá trị của biến đó từ một đường dẫn URL.*/
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id) throws PokemonNotFoundException {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }
    @GetMapping("pokemon/findName")
    public ResponseEntity<List<Pokemon>> getPokemonByName(@RequestParam("name") String name) {
        System.out.println("name = " + name);
        List<Pokemon> pokemonList = pokemonRepository.findPokemonByName(name);
        return ResponseEntity.ok(pokemonList);
    }
    @GetMapping("pokemon/findName1")
    public ResponseEntity<List<Pokemon>> getPokemonByName1(@RequestParam("name") String name) {
        System.out.println("name = " + name);
        List<Pokemon> pokemonList = pokemonRepository.findPokemonName(name);
        return ResponseEntity.ok(pokemonList);
    }



    @PostMapping("pokemon/create")
    /* 1. POST (Gửi dữ liệu):
    - Phương thức POST được sử dụng để gửi dữ liệu từ client lên server.
    - Khi gửi yêu cầu POST, dữ liệu được gửi thông qua phần body của yêu cầu HTTP.
    - POST thường được sử dụng để tạo, cập nhật hoặc xóa dữ liệu trên server.
    - Dữ liệu gửi đi bằng phương thức POST không được hiển thị trực tiếp trong URL.*/
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> create(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    //    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon){
//        System.out.println(pokemon.getName());
//        System.out.println(pokemon.getType());
//        return new  ResponseEntity<>(pokemon, HttpStatus.CREATED);
//    }
    @PutMapping("pokemon/{id}/update") //PUT được sử dụng để cập nhật (update) thông tin của một tài nguyên trên server
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) throws PokemonNotFoundException {
        PokemonDto response = pokemonService.updatePokemonById(pokemonDto, pokemonId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete") // sử dụng để xóa một tài nguyên trên server.
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) throws PokemonNotFoundException {
        pokemonService.deletePokemon(pokemonId);
        return new ResponseEntity<>("Pokemon success delete. ", HttpStatus.OK);
    }
    /* - findById(id): Tìm kiếm một đối tượng bằng ID.
       - findAll(): Truy vấn tất cả các đối tượng.
       - save(entity): Lưu hoặc cập nhật một đối tượng.
       - delete(entity): Xóa một đối tượng.
       - deleteById(id): Xóa một đối tượng dựa trên ID.
       - existsById(id): Kiểm tra xem một đối tượng có tồn tại trong cơ sở dữ liệu dựa trên ID hay không.
       - count(): Đếm số lượng đối tượng có trong cơ sở dữ liệu.
       - Ngoài ra, JpaRepository cũng hỗ trợ các phương thức truy vấn linh hoạt, ví dụ như:

        findByXXX(): Tìm kiếm các đối tượng dựa trên một thuộc tính cụ thể (ví dụ: findByFirstName(String firstName)).
        findByXXXAndYYY(): Tìm kiếm các đối tượng dựa trên hai hoặc nhiều thuộc tính (ví dụ: findByFirstNameAndLastName(String firstName, String lastName)).
        findByXXXOrderByYYY(): Tìm kiếm và sắp xếp các đối tượng dựa trên một thuộc tính (ví dụ: findByAgeOrderByLastNameDesc(int age)).*/

}
