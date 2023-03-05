package ua.sulima.mangaservletapp.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data @Builder
public class User {

    private long id;

    private String nickname;

    private String password;

    private String email;

    private Byte[] image;

    private Timestamp updated;

}
