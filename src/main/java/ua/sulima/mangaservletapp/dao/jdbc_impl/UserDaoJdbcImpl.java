package ua.sulima.mangaservletapp.dao.jdbc_impl;

import org.apache.commons.lang3.ArrayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.sulima.mangaservletapp.dao.UserDao;
import ua.sulima.mangaservletapp.dao.mapper.UserMapper;
import ua.sulima.mangaservletapp.entity.Role;
import ua.sulima.mangaservletapp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
public class UserDaoJdbcImpl extends ConnectionDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findById(Long id) {

        User user = null;
        String statement = "SELECT * FROM mangaapp.users WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println();
            if(resultSet.next()){
                user = new UserMapper().retrieveFromResultSet(resultSet);
            }
//            user = new UserMapper().retrieveFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
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
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
