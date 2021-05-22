package repository;
import entities.Accounts;

public interface AccountsRepository {
    Accounts get(String id);
    Accounts create(Accounts input);
    Accounts save(String id, Accounts input);
    Accounts saveBalance (String id, long UpdateBalanceAmount);

    Accounts getAccountNumber(String id);
    Accounts getBalance (String id);
    Accounts delete(String id);

}