package com.springboot.project4.project4backend.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "copies", nullable = false)
    private int copies;

    @Column(name = "copiesAvailable", nullable = false)
    private int copiesAvailable;

    @Lob
    @Column(name = "img", nullable = false, columnDefinition = "CLOB")
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
