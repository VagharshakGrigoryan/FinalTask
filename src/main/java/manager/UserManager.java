package manager;

import db.DBConnectionProvider;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final Connection connection = DBConnectionProvider.getProvider().getConnection();

    public void addUser(User user) {
        try {
            String query = "INSERT INTO task_manegement.user (`name`,`surname`,`email`,`password`,`type` ) " +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, user.getName());
            pStatement.setString(2, user.getSurname());
            pStatement.setString(3, user.getEmail());
            pStatement.setString(4, user.getPassword());
            pStatement.setString(5, user.getUserType().name());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE user SET name = " +
                            "'%s', surname = '%s', email='%s',password='%s',userType='%s' WHERE id=" + user.getId(),
                    user.getName(), user.getSurname(), user.getEmail(), user.getPassword(),user.getUserType());
            System.out.println(query);
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<User> getUsers() {
        String sql = "SELECT * FROM  task_manegement.user ";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString("type")))
                        .build();
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }


    public List<User> getAllUsers() {
        String sql = "SELECT * FROM  task_manegement.user ";
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString("type")))
                        .build();
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM task_manegement.user WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString("type")))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM task_manegement.user WHERE email='" + email + "' and password = '" + password + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surname(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .userType(UserType.valueOf(resultSet.getString("type")))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        String sql = "DELETE from task_manegement.user where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}

