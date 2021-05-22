package com.promineotech.zwkz.repository;

import com.promineotech.zwkz.entities.Accounts;
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
public class AccountsJdbcRepository implements AccountsRepository {

    @Autowired
    private NamedParameterJdbcTemplate _provider;

    @Override
    public Accounts get(String id) {
        String sql = "Select account_id" +
                " From accounts" +
                " Where account_id = :account_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account_id", id);
        List<Accounts> accounts = _provider.query(sql, parameters, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Accounts.builder()
                        .account_number(Integer.parseInt(rs.getString("account_id")))

                        .build();
            }
        });
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }

    public Accounts getByUserId(String userId) {
        String sql = "Select * from accounts Where user_id=:userId;";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", userId);
        return _provider.queryForObject(sql, parameters, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Accounts.builder()
                        .account_id(rs.getString("account_id"))
                        .user_id(rs.getString("user_id"))
                        .account_number(rs.getInt("account_number"))
                        .balance(rs.getInt("balance"))
                        .build();
            }
        });
    }

    @Override
    public Accounts getBalance(String id) {
        String sql = "Select balance" +
                " From accounts" +
                " Where account_id = :account_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account_id", id);
        List<Accounts> accounts = _provider.query(sql, parameters, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Accounts.builder()
                        .balance(Integer.parseInt(rs.getString("account_id")))

                        .build();
            }
        });
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }
    @Override
    public Accounts getAccountNumber(String id) {
        String sql = "Select account_number" +
                "From accounts" +
                "Where account_id = :account_id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("account_id", id);
        List<Accounts> accounts = _provider.query(sql, parameters, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Accounts.builder()
                        .account_number(Integer.parseInt(rs.getString("account_id")))

                        .build();
            }
        });
        if (accounts.isEmpty()) {
            return null;
        }
        return accounts.get(0);
    }
    @Override
    public Accounts create(Accounts input) {
        if (input != null) {
            String sql = "INSERT INTO accounts (user_id, account_number, balance)"
                    + "VALUES (:user_id, :account_number, :balance);";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("first_name", input.getAccount_number());
            parameters.addValue("last_name", input.getBalance());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return (get(input.getAccount_id()));
            }
        }
        return (null);
    }


    @Override
    public Accounts save(Accounts input) {
//        if ((id == null) || (id.isEmpty())) {
//            return (null);
//        }
        if (input == null) {
            return null;
        }
        //Check if User Exists
        Accounts existing = get(input.getAccount_id());
        if (existing != null) {
            // Update the item if exists
            String sql = "UPDATE accounts SET" +
                    " account_id = :account_id" +
                    " account_number = :account_number" +
                     " balance = :balance" +
                    " WHERE" +
                    " account_id = existing_account_id";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("account_id", input.getAccount_id());
            parameters.addValue("account_number", input.getAccount_number());
            parameters.addValue("balance", input.getBalance());

            int numRows = _provider.update(sql, parameters);
            if (numRows == 1) {
                return (get(input.getAccount_id()));
            }
        }
        return null;
    }


    @Override
    public Accounts saveBalance(String id, String UpdateBalanceAmount) {

        return null;
    }

    @Override
    public Accounts delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        // Get existing item
        Accounts existing = get(id);
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

    @Override
    public Accounts save(String id, Accounts input) {
        return null;
    }

}