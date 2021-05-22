package com.promineotech.zwkz.repository;

import com.promineotech.zwkz.entities.Users;

public interface UsersRepository {

    /**
     * Get's a userName based on it's unique id.
     * @param id The unique identifier
     * @return The instance of User if found, otherwise return null.
     */

    Users get(String id);


    /**
     * Creates a new title.
     * @param input The new title information.
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
}
