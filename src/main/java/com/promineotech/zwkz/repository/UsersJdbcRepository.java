package com.promineotech.zwkz.repository;

import com.promineotech.zwkz.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersJdbcRepository implements UsersRepository {
    @Autowired
    private NamedParameterJdbcTemplate _provider;

    @Override
    public Users get(String id) {
        String sql = "Select user_id,user_name" +
                "From users" +
                "Where user_id = :user_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", id);

        List<Users> users = _provider.query(sql, parameters, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Users.builder()
                        .user_id(rs.getString("user_id"))
                        .user_name(rs.getString("user_name"))
                        .build();
            }
        });
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public Users create(Users input) {
        if (input != null) {
            String sql = "INSERT INTO users  (first_name,last_name,user_name,password)"
                    + "VALUES (:first_name,:last_name,:user_name,:password);";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("first_name", input.getFirst_name());
            parameters.addValue("last_name", input.getLast_name());
            parameters.addValue("user_name", input.getUser_name());
            parameters.addValue("password", input.getPassword());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return (get(input.getUser_id()));
            }
        }
        return (null);
    }

    @Override
    public Users save(String id, Users input) {
        if ((id == null) || (id.isEmpty())) {
            return (null);
        }
        if (input == null) {
            return null;
        }
        //Check if User Exists
        Users existing = get(id);
        if (existing != null) {
            // Update the item if exists
            String sql = "UPDATE users SET" +
                    "user_id = :user_id" +
                    "first_name = :first_name" +
                    "last_name = :last_name" +
                    "user_name = :user_name" +
                    "password = :password" +
                    "WHERE" +
                    "user_id = existing_user_id";

            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("first_name", input.getFirst_name());
            parameters.addValue("last_name", input.getLast_name());
            parameters.addValue("user_name", input.getUser_name());
            parameters.addValue("password", input.getPassword());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return (get(input.getUser_id()));
            }

        }
        return (null);

    }

    @Override
    public Users delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }

        // Get existing item
        Users existing = get(id);
        if (existing != null) {
            String sql = "DELETE FROM title WHERE user_id = :user_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", id);

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(existing);
            }
        }
        return(null);
    }
}