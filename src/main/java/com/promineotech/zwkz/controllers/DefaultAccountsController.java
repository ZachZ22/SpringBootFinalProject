package com.promineotech.zwkz.controllers;
import com.promineotech.zwkz.entities.Accounts;
import com.promineotech.zwkz.entities.Users;
import com.promineotech.zwkz.services.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.zwkz.services.DefaultAccountsService;
@RestController
public class DefaultAccountsController implements AccountsController{
    private AccountsService _service;
    public DefaultAccountsController(AccountsService service){this._service = service;}
    @GetMapping(value = "/accounts/{id}")
    @Override
    public Accounts get(@PathVariable String id) {
        Accounts accounts = _service.get(id);
        if(accounts != null){
            return (accounts);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Account '%s' was not found.", id));
    }
    @GetMapping(value = "accounts/account_number/{id}")
    @Override
    public Accounts getAccountNumber(@PathVariable String id) {
        Accounts account_number = _service.get(id);
        if(account_number != null){
            return (account_number);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Account number '%s' was not found.", id));
    }



    @GetMapping(value = "accounts/balance/{id}")
    @Override
    public Accounts getBalance(@PathVariable String id) {
        Accounts balance= _service.get(id);
        if(balance != null){
            return (balance);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Balance '%s' was not found.", id));
    }
    @Override
    public Accounts delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required Account identifier was empty or missing"));
        }
        // Does the item actually exist?
        Accounts account = _service.get(id);
        if (account != null) {
            // If so, delete it
            Accounts deleted = _service.delete(id);
            if (deleted != null) {
                return(deleted);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Failed to delete Account '%s' due to an unhandled error.", id));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User '%s' was not found.", id));
    }
    @Override
    public Accounts create(Accounts input) {
        // TODO Auto-generated method stub
        return null;
    }
    @RequestMapping(value = "/accounts/update/{id}", method=RequestMethod.PUT)
    @Override
    public Accounts update(@PathVariable String id, @RequestBody Accounts input) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required Accounts identifier was empty or missing"));
        }
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("An empty or missing Accounts was provided"));
        }

        return(_service.save(id, input));
    }

}
