package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Đánh dấu là 1 lớp đối tượng trong database
@Data // Tạo getter và setter
@AllArgsConstructor // Tạo contructor có chứa đầy đủ các tham số
@NoArgsConstructor // Tạo contructor ko có chứa tham số
@Table(name = "Review") // Tên của bảng tương ứng trong database mà Entity tương ứng
public class    Review {
    @Id // Đánh dấu khóa chính trong table
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng auto-increment cho khóa chính (tự tăng MySql)
    @Column(name = "id") // Cộ trong bảng database

    private int id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "start")
    private int start;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Pokemon_id")
    private Pokemon pokemon;

}
