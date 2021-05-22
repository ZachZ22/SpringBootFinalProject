package controllers;

import entities.Posts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import services.PostsService;


@RestController
public class DefaultPostsController implements PostsController {
    private PostsService _service;

    public DefaultPostsController(PostsService service) {
        _service = service;
    }
    @RequestMapping(value = "/posts/{id}", method=RequestMethod.GET)
    @Override
    public Posts get(@PathVariable String id) {
        Posts posts = _service.get(id);
        if (posts != null) {
            return (posts);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Posts '%s' was not found.", id));
    }
    @RequestMapping(value = "/posts/{id}", method=RequestMethod.POST)
    @Override
    public Posts create(@RequestBody Posts input) {
        if (input != null) {
            Posts result = _service.create(input);
            if (result != null) {
                return(result);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Failed to create posts due to an unhandled error."));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("Posts input was empty or null"));
    }

    @RequestMapping(value = "/posts/{id}", method=RequestMethod.DELETE)
    @Override
    public Posts delete(@PathVariable String id) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required title identifier was empty or missing"));
        }

        // Does the item actually exist?
        Posts posts = _service.get(id);
        if (posts != null) {
            // If so, delete it
            Posts deleted = _service.delete(id);
            if (deleted != null) {
                return(deleted);
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Failed to delete posts '%s' due to an unhandled error.", id));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Posts '%s' was not found.", id));
    }
    @RequestMapping(value = "/posts/{id}", method=RequestMethod.PUT)
    @Override
    public Posts update(@PathVariable String id, @RequestBody Posts input) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required post identifier was empty or missing"));
        }
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("An empty or missing post was provided"));
        }

        return(_service.save(id, input));
    }

}