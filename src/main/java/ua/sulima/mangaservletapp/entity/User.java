package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data @Builder
public class User {

    private Long id;

    private String nickname;

    private String password;

    private String email;

    private Byte[] image;

    private Timestamp updated;

    private List<Role> roles;

}
