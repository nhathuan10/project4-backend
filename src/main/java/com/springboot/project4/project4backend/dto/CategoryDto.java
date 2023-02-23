package com.springboot.project4.project4backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private long id;
    @NotEmpty
    private String name;
    private List<BookDto> books = new ArrayList<>();
}
