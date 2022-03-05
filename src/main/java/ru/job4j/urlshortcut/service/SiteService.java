package ru.job4j.urlshortcut.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.exception.AccessDeniedException;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.repository.SiteRepository;

import java.net.URL;
import java.util.UUID;

@Service
public class SiteService {

    private final SiteRepository repository;

    private final PasswordEncoder encoder;

    public SiteService(SiteRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Site save(Site site) {
        Site result = null;
        if (!repository.existsByDomain(site.getDomain())) {
            String login = UUID.randomUUID().toString();
            String password = UUID.randomUUID().toString();
            site.setLogin(login);
            site.setPassword(encoder.encode(password));
            result = repository.save(site);
            site.setPassword(password);
        }
        return result;
    }

    public Link addLink(Link link) {
        URL url = link.getUrl();
        if (url == null) {
            throw new IllegalArgumentException("URL не может быть пустым");
        }
        String domain = url.getHost();
        if (!repository.existsByDomain(domain)) {
            throw new IllegalArgumentException("Домен не зарегистрирован");
        }
        Site site = repository.getByDomain(domain);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!user.equals(site.getLogin())) {
            throw new AccessDeniedException("У вас не прав на добавление ссылок к этому домену");
        }
        Link persisted = site.getLinks().stream()
                .filter(l -> l.getUrl().equals(url))
                .findFirst().orElse(null);
        if (persisted != null) {
            link = persisted;
        } else {
            link.setKey(UUID.randomUUID().toString());
            site.addLink(link);
            repository.save(site);
        }
        return link;
    }

    public Site getByName(String login) {
        return repository.getByLogin(login);
    }
}
