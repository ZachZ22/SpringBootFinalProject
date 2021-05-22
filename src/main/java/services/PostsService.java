package services;

import entities.Posts;

public interface PostsService {
    /**
     *
     * @param id
     * @return
     */
    Posts get(String id);

    /**
     *
     * @param input
     * @return
     */
    Posts create(Posts input);

    /**
     *
     * @param id
     * @return
     */
    Posts delete(String id);

    /**
     *
     * @param id
     * @param input
     * @return
     */
    Posts save(String id, Posts input);



}