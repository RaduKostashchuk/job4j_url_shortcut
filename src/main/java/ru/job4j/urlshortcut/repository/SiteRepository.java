package ru.job4j.urlshortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    boolean existsByDomain(String domain);

    Site getByLogin(String login);

    Site getByDomain(String domain);
}
