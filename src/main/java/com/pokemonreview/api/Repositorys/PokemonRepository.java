package com.pokemonreview.api.Repositorys;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.models.Pokemon;
import org.hibernate.mapping.Selectable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PokemonRepository  extends JpaRepository<Pokemon,Integer> {
    @Query( value = "SELECT p.* FROM Pokemon p ",nativeQuery = true)
    List<Pokemon> findPokemonByName(@Param("name") String name);

    @Query(value = "SELECT * From Pokemon p where p.name like %:name% ",nativeQuery = true)
    List<Pokemon> findPokemonName(@Param("name") String name);


    List<Pokemon>findByName (String name);

    /*JpaRepository là một interface trong Spring Data JPA, một phần của Spring Framework, được sử
    dụng để tương tác với cơ sở dữ liệu trong ứng dụng Java. JpaRepository mở rộng PagingAndSortingRepository
    và CrudRepository, và cung cấp các phương thức tiện ích để thực hiện các thao tác CRUD (Create, Read, Update, Delete) với cơ sở dữ liệu.

    JpaRepository cung cấp các phương thức chung để thực hiện các thao tác thông qua cơ sở dữ liệu, bao gồm:

   - Tìm kiếm và truy vấn đối tượng.
   - Lưu và cập nhật đối tượng.
   - Xóa đối tượng.
   - Kiểm tra sự tồn tại của đối tượng trong cơ sở dữ liệu.
   - Đếm số lượng đối tượng.
    Ngoài các phương thức tiện ích sẵn có, JpaRepository cũng hỗ trợ việc tạo ra các phương thức
    truy vấn tùy chỉnh dựa trên tên của các thuộc tính trong đối tượng. Bằng cách đặt tên phương thức theo quy ước đặt tên
    của Spring Data JPA, bạn có thể tự động tạo ra các phương thức truy vấn mà không cần viết SQL trực tiếp.

    JpaRepository được sử dụng để định nghĩa các repository (lớp trung gian) cho các đối tượng
    thực thể (entities) trong ứng dụng. Bằng cách sử dụng JpaRepository, bạn có thể tận dụng các phương thức tiện ích có sẵn
    để thao tác với cơ sở dữ liệu một cách dễ dàng và hiệu quả.

    Để sử dụng JpaRepository, bạn cần tạo một interface mở rộng JpaRepository, và chỉ định kiểu
    dữ liệu của đối tượng thực thể và kiểu dữ liệu của khóa chính trong interface đó. Sau đó, Spring Boot sẽ tự động cung c
    ấp các phương thức tiện ích cho bạn để thao tác với cơ sở dữ liệu thông qua interface đó.
    public interface PokemonRepository  extends JpaRepository<Pokemon,Integer>   <T,ID>
    T : Đây là kiểu dữ liệu của đối tượng thực thể (entity) mà repository sẽ làm việc
    ID : Đây là kiểu dữ liệu của khóa chính (ID) của đối tượng thực thể*/
}
