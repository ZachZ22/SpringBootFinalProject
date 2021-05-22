package services;

import entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import repository.UsersJdbcRepository;

public class DefaultUsersService implements UsersService{
    private UsersJdbcRepository _repository;
//    public DefaultUsersService(UsersRepository repository){_repository = repository;}

    @Autowired
    private Users user;


    // @Autowired
    private DefaultFriendsService friendService;

    @Override
    public Users get(String id) {
        if ((id == null) || (id.isEmpty())){
            return (null);
        }
       return _repository.get(id);
    }

    @Override
    public Users create(Users input) {
        if(input != null){
            Users existing = get(input.getUser_name());
            if(existing == null){
                return (_repository.create(input));
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

        return (_repository.save(id, input));
    }

    @Override
    public Users delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return(null);
        }
        return(_repository.delete(id));
    }

    @Override
    public Users updateTransaction(String id1, String id2, double amountToSend) {
       Users user1 = _repository.get(id1);
       Users user2 = _repository.get(id2);



       if((user1 != null) && (user2 !=null)){
           //update this user's account balance

           // this logic should come from the account's service
           // where it'll deduct the acmount from sender's account and
           //add it to receiver's account

           // assign the sender's details to the user from line 14

           //account service should include two methos for sure
           // and these two methods should be 1. reduces the balance
           //2. adds the money into the account
       }
       return user;



    }

    //check to see if the sender's id exists first
    /**
     * if sender's id exists, then reduce teh balance from their account
     *
     * Then, also check if the receiver's id exists
     * if exists then, add the reduced amount from sender's account to the receiver's account
     *
     *
     *
     */


}