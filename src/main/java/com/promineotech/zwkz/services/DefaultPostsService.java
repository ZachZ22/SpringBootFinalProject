package com.promineotech.zwkz.services;

import com.promineotech.zwkz.entities.Posts;
import org.springframework.stereotype.Service;
import com.promineotech.zwkz.repository.PostsRepository;

@Service
public class DefaultPostsService implements PostsService {
    private PostsRepository _postsRepository;

    public DefaultPostsService(PostsRepository repository) { _postsRepository = repository;
    }

    @Override
    public Posts get(String id) {
        if ((id == null) || (id.isEmpty())) {
            return (null);
        }
        return _postsRepository.get(id);
    }

    @Override
    public Posts create(Posts input) {
        if(input != null) {
            Posts existing = get(input.getPost_id());
            if (existing == null) {
                return (_postsRepository.create(input));
            }
        }
        return (null);
    }

    @Override
    public Posts delete(String id) {
        if((id == null) || (id.isEmpty())) {
            return(null);
        }
        return (_postsRepository.delete(id));
    }

    @Override
    public Posts save(Posts input) {
        return null;
    }

    @Override
    public Posts save(String id, Posts input) {
        if((id == null) || (id.isEmpty())) {
            return (null);
        }
        if (input == null) {
            return(null);
        }
        String newId = input.getPost_id();
        if((newId == null) || (newId.isEmpty())) {
            input.setPost_id(id);
        }
        return (_postsRepository.save(id,input));
    }

}