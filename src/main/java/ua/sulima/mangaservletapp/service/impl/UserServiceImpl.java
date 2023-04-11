package ua.sulima.mangaservletapp.service.impl;

import lombok.RequiredArgsConstructor;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.entity.User;
import ua.sulima.mangaservletapp.factories.dao.DaoFactory;
import ua.sulima.mangaservletapp.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final DaoFactory daoFactory;
    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        try(UserDao userDao = daoFactory.getUserDao()){
            user = userDao.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Long saveUser(User userToSave) {
        Long newUserId = 0L;
        try(UserDao userDao = daoFactory.getUserDao()){
            newUserId = userDao.save(userToSave);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newUserId;
    }
}
