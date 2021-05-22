package com.promineotech.zwkz.services;

import com.promineotech.zwkz.entities.Accounts;
import com.promineotech.zwkz.entities.Users;
import com.promineotech.zwkz.repository.AccountsRepository;
import com.promineotech.zwkz.repository.UsersRepository;
import org.springframework.stereotype.Service;


@Service
public class DefaultUsersService implements UsersService{
    private UsersRepository _usersRepository;
    private AccountsRepository _accountsRepository;

    public DefaultUsersService(UsersRepository usersRepository, AccountsRepository accountsRepository){
        this._usersRepository = usersRepository;
        this._accountsRepository = accountsRepository;
    }

    //@Autowired
    //private Users user;


    // @Autowired
   // private DefaultFriendsService friendService;

    @Override
    public Users get(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
       return _usersRepository.get(id);
    }

    @Override
    public Users create(Users input) {
        if(input != null){
            Users existing = get(input.getUser_name());
            if(existing == null){
                return (_usersRepository.create(input));
            }
        }
        return (null);
    }


    @Override
    public Users save(String id, Users input) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        if (input == null) {
            return(null);
        }

        String newId = input.getUser_id();
        if ((newId == null) || (newId.isEmpty())) {
            input.setUser_id(id);
        }

        return (_usersRepository.save(id,input));
    }

    @Override
    public Users delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        return(_usersRepository.delete(id));
    }

    @Override
    public Users sendMoney(String senderId, String receivingId, double amountToSend) {
       Users sender = _usersRepository.get(senderId);
       Users receiver = _usersRepository.get(receivingId);
       Accounts senderAccount = _accountsRepository.getByUserId(senderId);
       Accounts receiverAccount = _accountsRepository.getByUserId(receivingId);
       if((sender != null) && (receiver !=null)){
           //update this user's account balance
            senderAccount.setBalance((int)(senderAccount.getBalance() - amountToSend));
            receiverAccount.setBalance((int)(receiverAccount.getBalance() + amountToSend));
           // this logic should come from the account's service.
           // where it'll deduct the account from sender's account and
           //add it to receiver's account
            _accountsRepository.save(senderId, senderAccount); // try catch
            _accountsRepository.save(receivingId, receiverAccount); // try catch
           // assign the sender's details to the user from line 14

           //account service should include two methos for sure
           // and these two methods should be 1. reduces the balance
           //2. adds the money into the account
       }
       return null;
    }

    //check to see if the sender's id exists first
    /**
     * if sender's id exists, then reduce the balance from their account
     *
     * Then, also check if the receiver's id exists
     * if exists then, add the reduced amount from sender's account to the receiver's account
     *
     *
     *
     */


}