package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();}

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255) NOT NULL, lastName VARCHAR(255) NOT NULL, age INT NOT NULL)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);}
        catch (SQLException e) {
            System.err.println("Ошибка при создании таблицы: " + e.getMessage());}
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);}
        catch (SQLException e) {
            System.err.println("Ошибка при удалении таблицы: " + e.getMessage());}
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users(name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("User: " + name + " добавлен в базу данных");}
        catch (SQLException e) {
            System.err.println("Ошибка при сохранении пользователя: " + e.getMessage());}
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = " + id;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);}
        catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());}
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                list.add(new User(id, name, lastName, age));}
        } catch (SQLException e) {
            System.err.println("Ошибка при получении всех пользователей: " + e.getMessage());}
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);}
        catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы: " + e.getMessage());}
    }
}
