package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Creator {

    private int id;

    private String name;

    private String description;
}
