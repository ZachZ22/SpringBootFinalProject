package services;

import entities.Users;

public interface UsersService {


    /**
     * Get's a User based on it's unique id.
     * @param id The unique identifier
     * @return The instance of User if found, otherwise return null.
     */
    Users get(String id);

    /**
     * Creates a new User.
     * @param input The new User information.
     * @return The created title if successful otherwise null
     */
    Users create(Users input);
    /**
     * Saves or modifies an existing User in the database.
     * @param id The unique id
     * @param input The new User information to modify or change.
     * @return The updated or modified User changes.
     */
    Users save(String id, Users input);

    /**
     * Deletes a User by its unique id.
     * @param id The unique id
     * @return The deleted User if successful, otherwise null
     */
    Users delete(String id);

    /**
     * jkdlajkfdljal;fjdl;aj
     * @param id1   is sender's id
     * @param id2   is the receiver's id
     * @return the sender
     */
    Users updateTransaction(String id1, String id2, double amountToSend);
}
