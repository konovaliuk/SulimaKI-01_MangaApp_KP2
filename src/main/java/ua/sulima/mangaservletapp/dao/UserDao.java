package ua.sulima.mangaservletapp.dao;

import ua.sulima.mangaservletapp.entity.User;

import java.util.Optional;

public interface UserDao extends BasicDao <User, Long>{

    Optional<User> findByEmail(String email);
}
