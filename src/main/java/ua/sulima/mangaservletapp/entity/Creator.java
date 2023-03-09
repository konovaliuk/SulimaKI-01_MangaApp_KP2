package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Creator {

    private Integer id;

    private String name;

    private String description;
}
