package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.LinkStat;

public interface LinkStatRepository extends CrudRepository<LinkStat, Integer> {
    LinkStat getByUrl(String url);
}
