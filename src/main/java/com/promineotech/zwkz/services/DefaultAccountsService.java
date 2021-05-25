package com.promineotech.zwkz.services;
import com.promineotech.zwkz.entities.Accounts;
import com.promineotech.zwkz.entities.Users;
import com.promineotech.zwkz.repository.AccountsJdbcRepository;
import com.promineotech.zwkz.repository.AccountsRepository;
import org.springframework.stereotype.Service;


@Service
public class DefaultAccountsService implements AccountsService {
    private AccountsRepository _accountsRepository;

    public DefaultAccountsService(AccountsRepository accountsRepository){
    this._accountsRepository =accountsRepository;
}
    @Override
    public Accounts get(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _accountsRepository.get(id);
    }
    @Override
    public Accounts getAccountNumber(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _accountsRepository.get(id);
    }
    @Override
    public Accounts getBalance(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _accountsRepository.get(id);
    }
    @Override
    public Accounts create(Accounts input) {
        if(input != null){
            Accounts existing = get(input.getAccount_id());
            if(existing == null){
                return (_accountsRepository.create(input));
            }
        }
        return (null);
    }
    @Override
    public Accounts save(String id, Accounts input) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        if (input == null) {
            return(null);
        }
        String newId = input.getAccount_id();
        if ((newId == null) || (newId.isEmpty())) {
            input.setAccount_id(id);
        }
        return (_accountsRepository.save(id, input));
   }

    @Override
    public Accounts delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        return(_accountsRepository.delete(id));
    }

}