package repository;

import entities.Posts;

public interface PostsRepository {
    /**
     * Get's a Post based on it's unique id
     *
     * @param id
     * @return
     */
    Posts get(String id);

    /**
     * Creates a new title
     *
     * @param input
     * @return
     */
    Posts create(Posts input);

    /**
     * Deletes a title by it's unique id
     *
     * @param id
     * @return
     */
    Posts delete(String id);

    /**
     * Saves or modifies an existing posts in the database
     *
     * @param id
     * @param input
     * @return
     */
    Posts save(String id, Posts input);



}