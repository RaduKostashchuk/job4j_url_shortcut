package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Link;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    Link getByKey(String key);
}
