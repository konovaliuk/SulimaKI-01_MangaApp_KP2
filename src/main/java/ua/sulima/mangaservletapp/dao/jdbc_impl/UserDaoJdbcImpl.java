package ua.sulima.mangaservletapp.dao.jdbc_impl;

import org.apache.commons.lang3.ArrayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.dao.mapper.RoleMapper;
import ua.sulima.mangaservletapp.dao.mapper.UserMapper;
import ua.sulima.mangaservletapp.entity.Role;
import ua.sulima.mangaservletapp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDaoJdbcImpl extends ConnectionDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findById(Long id) {

        User user = null;
        String findUserStatement = "SELECT * FROM mangaapp.users WHERE id = ?;";
        String findUserRolesStatement = "SELECT r.*\n" +
                "FROM mangaapp.role r\n" +
                "JOIN mangaapp.user_role ur\n" +
                "ON r.id = ur.role_id\n" +
                "WHERE ur.user_id = ?;";
        try (PreparedStatement findUserPreparedStatement =
                     connection.prepareStatement(findUserStatement);
             PreparedStatement findUserRolesPreparedStatement =
                     connection.prepareStatement(findUserRolesStatement)) {

            connection.setAutoCommit(false);

            findUserPreparedStatement.setLong(1, id);
            ResultSet resultSet = findUserPreparedStatement.executeQuery();
            if(resultSet.next()){
                user = new UserMapper().retrieveFromResultSet(resultSet);
            }

            findUserRolesPreparedStatement.setLong(1, id);
            resultSet = findUserRolesPreparedStatement.executeQuery();
            List<Role> userRolesList = new ArrayList<>();
            RoleMapper roleMapper = new RoleMapper();
            while(resultSet.next()){
                userRolesList.add(roleMapper.retrieveFromResultSet(resultSet));
            }
            user.setRoles(userRolesList);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
            }
        }
        return Optional.ofNullable(user);
    }



    @Override
    public Long save(User userToSave) {
        String saveUserStatement =
                "insert into mangaapp.users(email, nickname, password, image, updated)\n" +
                "values (?, ?, ?, ?, ?);";
        String saveUserRolesStatement = "insert into mangaapp.user_role(user_id, role_id)\n" +
                "values (?, ?);";
        Long generatedId = 0l;

        try (PreparedStatement saveUser = connection.prepareStatement(saveUserStatement
                ,PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement saveUserRoles = connection.prepareStatement(saveUserRolesStatement)) {

            connection.setAutoCommit(false);
            saveUser.setString(1, userToSave.getEmail());
            saveUser.setString(2, userToSave.getNickname());
            saveUser.setString(3, userToSave.getPassword());
            saveUser.setBytes(4, ArrayUtils.toPrimitive(userToSave.getImage()));
            saveUser.setTimestamp(5, userToSave.getUpdated());

            int count = saveUser.executeUpdate();
            if (count == 0) {
                connection.rollback();
                throw new RuntimeException("User already exists");
            }

            ResultSet resultSet = saveUser.getGeneratedKeys();
            resultSet.next();
            generatedId = resultSet.getLong(1);
            for(Role role: userToSave.getRoles()){
                saveUserRoles.setLong(1, generatedId);
                saveUserRoles.setShort(2, role.getId());
                saveUserRoles.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
            }
        }
        return generatedId;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        Optional<User> user = Optional.empty();
        String findUserStatement = "SELECT * FROM mangaapp.users WHERE email = ?;";
        String findUserRolesStatement = "SELECT r.*\n" +
                "FROM mangaapp.role r\n" +
                "JOIN mangaapp.user_role ur\n" +
                "ON r.id = ur.role_id\n" +
                "WHERE ur.user_id = ?;";
        try (PreparedStatement findUserPreparedStatement =
                     connection.prepareStatement(findUserStatement);
             PreparedStatement findUserRolesPreparedStatement =
                     connection.prepareStatement(findUserRolesStatement)) {

            connection.setAutoCommit(false);

            findUserPreparedStatement.setString(1, email);
            ResultSet resultSet = findUserPreparedStatement.executeQuery();
            if(resultSet.next()){
                user = Optional.of(new UserMapper().retrieveFromResultSet(resultSet));
            }else{
                return user;
            }
            Long userId = user.get().getId();

            findUserRolesPreparedStatement.setLong(1, userId);
            resultSet = findUserRolesPreparedStatement.executeQuery();
            List<Role> userRolesList = new ArrayList<>();
            RoleMapper roleMapper = new RoleMapper();
            while(resultSet.next()){
                userRolesList.add(roleMapper.retrieveFromResultSet(resultSet));
            }
            user.get().setRoles(userRolesList);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    log.info("Transaction is being rolled back");
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException exception) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

}
