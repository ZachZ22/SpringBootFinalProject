package com.promineotech.zwkz.controllers;
import com.promineotech.zwkz.entities.Accounts;
import com.promineotech.zwkz.entities.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface AccountsController {
    /**
     * Get's a title based on it's unique id.
     * @param id The unique identifier
     * @return The instance of User if found, otherwise return null.
     */
    Accounts get(String id);
    /**
     * Creates a new title.
     * @param input The new title information.
     * @return The created title if successful, otherwise null
     */
    Accounts create(Accounts input);
    /**
     * Updates or applies the changes to the specified User.
     * @param id The unique id
     * @param input The updated User information.
     * @return The updated or modified, otherwise null
     */
    Accounts update(String id, Accounts input);


    Accounts getBalance(String id);

    /**
     * Deletes a User by its unique id.
     * @param id The unique id
     * @return The deleted User if successful, otherwise null
     */
    Accounts delete(String id);
    Accounts getAccountNumber(String id);


}