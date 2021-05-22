package repository;

import entities.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class FriendsJdbcRepository implements FriendsRepository {

    @Autowired
    private NamedParameterJdbcTemplate _provider;


    @Override
    public Friends get(String id) {
        String sql = "Select user_id,user_name, first_name, last_name" +
                "From friends" +
                "Where user_id = :user_id";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.getValue("user_id");
        parameters.getValue("user_name");
        parameters.getValue("first_name");
        parameters.getValue("last_name");


        int numRows = -_provider.update(sql, parameters);
        if(numRows == 1){

        }
        return null;

    }
}
