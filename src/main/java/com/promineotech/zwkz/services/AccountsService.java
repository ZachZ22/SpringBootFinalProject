package com.promineotech.zwkz.services;
import com.promineotech.zwkz.entities.Accounts;

public interface AccountsService {
    Accounts get(String id);
    Accounts create(Accounts input);
    Accounts save(String id, Accounts input);
    Accounts delete(String id);
    Accounts getAccountNumber(String id);
    Accounts getBalance (String id);
}
