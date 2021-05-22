package com.promineotech.zwkz.repository;
import com.promineotech.zwkz.entities.Accounts;

public interface AccountsRepository {
    Accounts get(String id);
    Accounts create(Accounts input);
    Accounts save(String id, Accounts input);
    Accounts saveBalance (String id, String UpdateBalanceAmount);

    Accounts getAccountNumber(String id);
    Accounts getBalance (String id);
    Accounts delete(String id);

}