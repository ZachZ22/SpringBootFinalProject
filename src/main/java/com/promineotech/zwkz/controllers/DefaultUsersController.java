package com.promineotech.zwkz.controllers;

import com.promineotech.zwkz.entities.Users;
import com.promineotech.zwkz.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.zwkz.services.DefaultUsersService;

@RestController
public class DefaultUsersController implements UsersController{
    private UsersService _service;

    public DefaultUsersController(UsersService service){this._service = service;}
    @GetMapping(value = "/users/{id}")
    @Override
    public Users get(@PathVariable String id) {
        Users users = _service.get(id);
        if(users != null){
            return (users);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User '%s' was not found.", id));
    }

    @RequestMapping(value = "/user_name", method=RequestMethod.POST)
    @Override
    public Users create(@RequestBody Users input) {
        if(input != null){
            Users result = _service.create(input);
            if(result != null){
                return (result);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Failed to create User due to an unhandled error."));
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                String.format("User input was empty or null"));
    }

    @RequestMapping(value = "/users/update/{id}", method=RequestMethod.PUT)
    @Override
    public Users update(@PathVariable String id, @RequestBody Users input) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required User identifier was empty or missing"));
        }
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("An empty or missing User was provided"));
        }

        return(_service.save(id, input));
    }

    @Override
    public Users delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The required User identifier was empty or missing"));
        }

        // Does the item actually exist?
        Users user = _service.get(id);
        if (user != null) {
            // If so, delete it
            Users deleted = _service.delete(id);
            if (deleted != null) {
                return(deleted);
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    String.format("Failed to delete User '%s' due to an unhandled error.", id));
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User '%s' was not found.", id));
    }
}
