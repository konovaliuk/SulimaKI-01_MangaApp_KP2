package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class UserRole {

    private Long userId;

    private Short roleId;
}
