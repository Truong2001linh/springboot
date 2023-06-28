package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // Đánh dấu là 1 lớp đối tượng trong database
@Data // Tạo getter và setter
@AllArgsConstructor // Tạo contructor có chứa đầy đủ các tham số
@NoArgsConstructor // Tạo contructor ko có chứa tham số
@Table(name = "pokemon") // Tên của bảng tương ứng trong database mà Entity tương ứng
public class Pokemon {
    @Id // Đánh dấu khóa chính trong table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng auto-increment cho khóa chính (tự tăng MySql)
    @Column(name = "id") // Cộ trong bảng database
    private  Integer id;

    @Column(name = "name")
    private String name ;
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<Review>(   );


    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
