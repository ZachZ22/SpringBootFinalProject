package com.promineotech.zwkz.repository;

import com.promineotech.zwkz.entities.Friends;

public interface FriendsRepository {
    /**
     * Get's a title based on it's unique id.
     * @param id The unique identifier
     * @return The instance of User if found, otherwise return null.
     */
    Friends get(String id);
}
