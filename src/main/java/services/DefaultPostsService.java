package services;

import entities.Posts;
import org.springframework.stereotype.Service;
import repository.PostsRepository;

@Service
public class DefaultPostsService implements PostsService {
    private PostsRepository _repository;

    public DefaultPostsService(PostsRepository repository) {
        _repository = repository;
    }

    @Override
    public Posts get(String id) {
        if ((id == null) || (id.isEmpty())) {
            return (null);
        }
        return _repository.get(id);
    }

    @Override
    public Posts create(Posts input) {
        if(input != null) {
            Posts existing = get(input.getPost_id());
            if (existing == null) {
                return (_repository.create(input));
            }
        }
        return (null);
    }

    @Override
    public Posts delete(String id) {
        if((id == null) || (id.isEmpty())) {
            return(null);
        }
        return (_repository.delete(id));
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
        return (_repository.save(id, input));
    }

}