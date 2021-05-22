package services;
import entities.Accounts;

public class DefaultAccountsService implements AccountsService {
    private AccountsJdbcRepository _repository;
    //public DefaultAccountsService(AccountsRepository repository){_repository = repository;}
    @Override
    public Accounts get(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _repository.get(id);
    }
    @Override
    public Accounts getAccountNumber(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _repository.get(id);
    }
    @Override
    public Accounts getBalance(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
        return _repository.get(id);
    }
    @Override
    public Accounts create(Accounts input) {
        if(input != null){
            Accounts existing = get(input.getAccount_id());
            if(existing == null){
                return (_repository.create(input));
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
        return (_repository.save(id, input));
    }


    @Override
    public Accounts delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        return(_repository.delete(id));
    }

}