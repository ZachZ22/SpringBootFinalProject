package repository;

import entities.Posts;
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
        String sql = "SELECT post " +
                "FROM posts " +
                "WHERE post_id = : post_id";
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("post_Id", id);

        List<Posts> post = _provider.query(sql, parameters, new RowMapper<Posts>() {

            @Override
            public Posts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Posts.builder()
                        .post_id(rs.getString("post_id"))
                        .post(rs.getString("post"))
                        .build();
            }
        });
        if (post.isEmpty()) {
            return null;
        }
        return post.get(0);
    }

    @Override
    public Posts create(Posts input) {
        if (input != null) {
            String sql = "INSERT INTO post(user_id, date_time, post) " +
                    "VALUES (:user_id, :date_time, : post);";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("post_id", input.getPost_id());
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("post", input.getPost());
            parameters.addValue("date_time", input.getDate_time());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(get(input.getPost_id()));
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
            String sql = "DELETE FROM posts " +
                    "WHERE post_id = :post_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("posts_id", id);

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(existing);
            }
        }
        return null;

    }

    @Override
    public Posts save(String id, Posts input) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        //Check if items exist
        Posts existing = get(id);
        if (existing != null) {
            //Update existing item
            String sql = "UPDATE post SET " +
                    "post_id = :title_id, " +
                    "user_id = :user_id, " +
                    "post = :post, " +
                    "date_time = :date_time";

            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("post_id", input.getPost_id());
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("post", input.getPost());
            parameters.addValue("date_time", input.getDate_time());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return(get(input.getPost_id()));
            }
        }
        return null;
    }

}