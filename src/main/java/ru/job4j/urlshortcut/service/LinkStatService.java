package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.LinkStat;
import ru.job4j.urlshortcut.repository.LinkStatRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LinkStatService {
    private final LinkStatRepository repository;

    public LinkStatService(LinkStatRepository repository) {
        this.repository = repository;
    }

    public void save(LinkStat linkStat) {
        repository.save(linkStat);
    }

    public void update(LinkStat linkStat) {
        repository.update(linkStat.getUrl());
    }

    public LinkStat getByUrl(String url) {
        return repository.getByUrl(url);
    }

    public List<LinkStat> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
