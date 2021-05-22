package controllers;

import entities.Posts;

public interface PostsController {
    /**
     *
     * @param id
     * @return
     */
    Posts get(String id);

    /**
     *
     * @param Input
     * @return
     */

    Posts create(Posts Input);
    /**
     *
     * @param id
     * @return
     */
    Posts delete (String id);
    /**
     *
     * @param id
     * @param input
     * @return
     */
    Posts update (String id, Posts input);




}