package com.promineotech.zwkz.repository;

import com.promineotech.zwkz.entities.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class PostsJdbcRepository implements PostsRepository {
    @Autowired
    private NamedParameterJdbcTemplate _provider;

    @Override
    public Posts get(String id) {
        //SELECT post FROM posts WHERE post_id = 4;
        String sql = "SELECT post_id,post" +
                " FROM posts" +
                " WHERE post_id = :post_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("post_id", id);

        List<Posts> post = _provider.query(sql, parameters, new RowMapper<Posts>() {

            @Override
            public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Posts.builder()
                        .post_id(rs.getString("post_id"))
                        .post(rs.getString("post"))
                        .build();
            }
        });
        if (post.isEmpty())  {
            return null;
        }
        return post.get(0);
    }

    @Override
    public Posts create(Posts input) {
        if (input != null) {
            //INSERT INTO posts (user_id, date_time, post) values (1, now(), "Yo Zach, Heres the money I lost to you in poker last night." );
            String sql = "INSERT INTO posts (user_id,date_time,post) " +
                    "VALUES (:user_id, :date_time, :post);";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            //parameters.addValue("post_id", input.getPost_id());
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("date_time", input.getDate_time());
            parameters.addValue("post", input.getPost());


            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(get(input.getPost()));
            }
        }
        return null;
    }

    @Override
    public Posts delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        //Get existing item
        Posts existing = get(id);
        if (existing != null) {
            String sql = "DELETE FROM posts WHERE post_id = :post_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("post_id", id);

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(existing);
            }
        }
        return null;

    }


    @Override
    public Posts save(String id,Posts input) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        if (input == null) {
            return(null);
        }

        //Check if items exist
       // Posts existing = get(input.getPost_id());
        Posts existing = get(id);
        if (existing != null) {
            //Update existing item
            String sql = "UPDATE posts SET " +
                    " post = :post " +
                    " WHERE " +
                    " post_id = :existing_post_id ";

            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("post_id", input.getPost_id());
            //parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("post", input.getPost());
            //parameters.addValue("date_time", input.getDate_time());
            parameters.addValue("existing_post_id", id);
            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                try {
                    Posts returnValue = get(input.getPost());
                    return (returnValue);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw ex;
                }
            }
        }
        return null;
    }



}