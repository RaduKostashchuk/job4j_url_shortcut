package ru.job4j.urlshortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.model.Site;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SiteService service;

    public UserDetailsServiceImpl(SiteService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Site site = service.getByName(username);

        if (site == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(site.getLogin(), site.getPassword(), Collections.emptyList());
    }
}
