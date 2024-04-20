package com.atharv.taskmangercli.repository;

import com.atharv.taskmangercli.beans.Tasks;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TaskRepository {
    private final JdbcTemplate springJdbcTemplate;

    private static String INSERT_QUERY = """
            insert into Task values(?,?,?,?,?);
            """;

    private static String UPDATE_QUERY = """
            update task
            set status = TRUE
            where id = ?;
            """;

    private static String DELETE_QUERY = """
            delete from task where id = ?;
            """;

    private static String READ_QUERY = """
            select * from task;
            """;

    private static String CHECK_QUERY = """
            select id from task where id = ?;
            """;

    public TaskRepository(JdbcTemplate springJdbcTemplate) {
        this.springJdbcTemplate = springJdbcTemplate;
    }

    public void insert(int id, Tasks tasks) {
        springJdbcTemplate.update(INSERT_QUERY, id, tasks.getTitle(), tasks.getDescription(), tasks.getCategory(), tasks.isCompleted());
    }

    public void updateById(int id) {
        springJdbcTemplate.update(UPDATE_QUERY, id);
    }

    public void deleteById(int id) {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public void readALl() {
        springJdbcTemplate.query(READ_QUERY, (ResultSet rs) -> {
            try {
                if (rs != null)
                    do {
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Title: " + rs.getString("title"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("Category: " + rs.getString("category"));
                        System.out.println("Status: " + rs.getBoolean("status"));
                        System.out.println();
                    } while (rs.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public boolean isId(int id) {
        try {
            springJdbcTemplate.queryForObject(CHECK_QUERY, Integer.class, id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
