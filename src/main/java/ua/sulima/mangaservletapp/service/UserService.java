package ua.sulima.mangaservletapp.service;

import ua.sulima.mangaservletapp.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByEmail(String email);

    Long saveUser(User userToSave);
}
