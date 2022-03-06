package ru.job4j.urlshortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.LinkStat;
import ru.job4j.urlshortcut.repository.LinkRepository;

import java.util.NoSuchElementException;

@Service
public class LinkService {
    private final LinkRepository links;
    private final LinkStatService stats;

    public LinkService(LinkRepository links, LinkStatService stats) {
        this.links = links;
        this.stats = stats;
    }

    public Link getByKey(String key) {
        Link result = links.getByKey(key);
        if (result == null) {
            throw new NoSuchElementException("Такой ссылки не существует");
        }
        updateStat(result);
        return result;
    }

    private void updateStat(Link link) {
        LinkStat linkStat = stats.getByUrl(link.getUrl().toString());
        if (linkStat == null) {
            linkStat = LinkStat.of(link.getUrl().toString());
            stats.save(linkStat);
        } else {
            stats.update(linkStat);
        }
    }
}
