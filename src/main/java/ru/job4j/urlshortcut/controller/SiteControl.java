package ru.job4j.urlshortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlshortcut.dto.LinkDTO;
import ru.job4j.urlshortcut.dto.RegDTO;
import ru.job4j.urlshortcut.model.Link;
import ru.job4j.urlshortcut.model.LinkStat;
import ru.job4j.urlshortcut.model.Site;
import ru.job4j.urlshortcut.service.LinkService;
import ru.job4j.urlshortcut.service.LinkStatService;
import ru.job4j.urlshortcut.service.SiteService;

import java.net.URISyntaxException;
import java.util.List;

@RestController
public class SiteControl {

    private final SiteService sites;

    private final LinkService links;

    private final LinkStatService stats;

    public SiteControl(SiteService sites, LinkService links, LinkStatService stats) {
        this.sites = sites;
        this.links = links;
        this.stats = stats;
    }

    @PostMapping("/registration/")
    public ResponseEntity<RegDTO> registration(@RequestBody Site site) {
        site = sites.save(site);
        RegDTO result = site != null
                ? RegDTO.of(true, site.getLogin(), site.getPassword())
                : RegDTO.of(false, "", "");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/convert/")
    public ResponseEntity<LinkDTO> convert(@RequestBody Link link) {
        link = sites.addLink(link);
        return new ResponseEntity<>(LinkDTO.of(link.getKey()), HttpStatus.OK);
    }

    @GetMapping("/redirect/{key}")
    public ResponseEntity<Void> redirect(@PathVariable String key) throws URISyntaxException {
        Link link = links.getByKey(key);
        return ResponseEntity.status(HttpStatus.FOUND).location(link.getUrl().toURI()).build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<LinkStat>> getStat() {
        return new ResponseEntity<>(stats.getAll(), HttpStatus.OK);
    }
}
