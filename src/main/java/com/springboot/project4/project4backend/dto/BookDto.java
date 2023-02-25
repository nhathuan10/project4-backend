package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long id;

    @NotEmpty
    @Size(min = 5, message = "Book title should have at least 2 characters")
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    @Size(max = 255, message = "Book description should have no more than 255 characters")
    @Size(min = 10, message = "Book description should have at least 10 characters")
    private String description;

    private int copies;

    private int copiesAvailable;

    @NotEmpty
    private String img;

    private String categoryName;

    private List<ReviewDto> reviews = new ArrayList<>();
}
