package service;

import io.vavr.collection.List;
import model.Todo;
import repository.TodoRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.vavr.collection.List.ofAll;

public class TodoService {
    @Inject
    private TodoRepository repository;

    public List<Todo> findAll() {
        return ofAll(repository.findAll());
    }

    @Transactional
    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    @Transactional
    public Todo update(Todo todo) {
        return repository.save(todo);
    }

    @Transactional
    public void remove(Long id) {
        repository.remove(findBy(id));
    }

    public Todo findBy(Long todoId) {
        return repository.findBy(todoId);
    }
}