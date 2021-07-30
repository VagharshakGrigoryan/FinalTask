package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final Connection connection = DBConnectionProvider.getProvider().getConnection();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private final UserManager userManager = new UserManager();


    public void addtask(Task task) {
        try {

            String query = "INSERT INTO task_manegement.task (`name`,`description`,`deadline`,`status`,`user_id`) " +
                    "VALUES(?,?,?,?,?);";
            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, task.getName());
            pStatement.setString(2, task.getDescription());
            pStatement.setString(3, sdf.format(task.getDeadline()));
            pStatement.setString(4, task.getTaskStatus().name());
            pStatement.setInt(5, task.getUserId());
            System.out.println(query);
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                task.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTaskStatus(int taskId,String newStatus) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE task_manegement.task set status = ? where id = ?");
            preparedStatement.setString(1,newStatus);
            preparedStatement.setInt(2,taskId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getAlltasks() {

        String sql = "SELECT * from task_manegement.task";
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .deadline(resultSet.getDate(4))
                        .taskStatus(TaskStatus.valueOf(resultSet.getString("status")))
                        .userId(resultSet.getInt("user_id"))
                        .build();
                tasks.add(task);
                task.setUser(userManager.getUserById(task.getUserId()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getTaskFromeResultSet() {
        String sql = "SELECT * from task_manegement.task";
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .deadline(resultSet.getDate(4))
                        .taskStatus(TaskStatus.valueOf(resultSet.getString("status")))
                        .userId(resultSet.getInt("user_id"))
                        .build();
                tasks.add(task);
                task.setUser(userManager.getUserById(task.getUserId()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTaskByUserId(int user_id) {
        String sql = "SELECT * from task_manegement.task";
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .deadline(resultSet.getDate(4))
                        .taskStatus(TaskStatus.valueOf(resultSet.getString("status")))
                        .userId(resultSet.getInt("user_id"))
                        .build();
                tasks.add(task);
                task.setUser(userManager.getUserById(task.getUserId()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tasks;
    }

    public void deletetask(int id) {
        String sql = "DELETE from task_manegement.task where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
