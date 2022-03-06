package ru.job4j.urlshortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.model.LinkStat;

import javax.transaction.Transactional;

public interface LinkStatRepository extends CrudRepository<LinkStat, Integer> {
    LinkStat getByUrl(String url);

    @Transactional
    @Modifying
    @Query(value = "UPDATE statistic SET counter = counter + 1 WHERE url = ?", nativeQuery = true)
    void update(String url);
}
