package com.spring.security.Spring.Security.controller;

import com.spring.security.Spring.Security.model.Todo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/v1")
public class TodoController {

    private static Logger logger = LoggerFactory.getLogger(TodoController.class);

    List<Todo> todos = new ArrayList<>();

    @PostConstruct
    void init() {
        todos.add(new Todo("name 1", "description 1"));
        todos.add(new Todo("name", "description 2"));
        todos.add(new Todo("name 3", "description 3"));
        todos.add(new Todo("name", "description 4"));
        todos.add(new Todo("name 5", "description 5"));
    }

    @GetMapping(path = "/todos")
    public List<Todo> getAllTodos() {
        return todos;
    }

    @GetMapping(path = "/users/{username}/todos")
    public List<Todo> getAllTodoForSpecificUser(@PathVariable String username) {
        return todos.stream().filter(todo -> todo.getUsername().equals(username)).collect(Collectors.toList());
    }

    @PostMapping(path = "/users/{username}/todos")
    public void createTodoForSpecificUser(@PathVariable String username, @RequestBody Todo todo) {
        logger.info("create {} for {}", todo, username);
    }
}

