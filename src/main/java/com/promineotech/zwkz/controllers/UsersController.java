package com.promineotech.zwkz.controllers;

import com.promineotech.zwkz.entities.Users;

public interface UsersController {

    /**
     * Get's a title based on it's unique id.
     * @param id The unique identifier
     * @return The instance of User if found, otherwise return null.
     */
    Users get(String id);
    /**
     * Creates a new title.
     * @param input The new title information.
     * @return The created title if successful, otherwise null
     */
    Users create(Users input);
    /**
     * Updates or applies the changes to the specified User.
     * @param id The unique id
     * @param input The updated User information.
     * @return The updated or modified, otherwise null
     */
    Users update(String id, Users input);
    /**
     * Deletes a User by its unique id.
     * @param id The unique id
     * @return The deleted User if successful, otherwise null
     */
    Users delete(String id);
}
