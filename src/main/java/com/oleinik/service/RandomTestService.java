package com.oleinik.service;

import com.oleinik.cache.IndexedIdCache;
import com.oleinik.entity.LangUnit;
import com.oleinik.entity.User;
import com.oleinik.exception.TestNotGeneratedException;
import com.oleinik.repository.LangUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandomTestService implements TestService {

    @Autowired
    private IndexedIdCache indexedIdCache;

    @Autowired
    private LangUnitRepository langUnitRepository;

    @Autowired
    private UserService userService;

    private List<LangUnit> langUnitsTest;

    @Override
    public LangUnit getUnitFromTest(int index) {
        if (langUnitsTest == null) throw new TestNotGeneratedException("Test has not been generated");
        return langUnitsTest.get(index);
    }

    @Override
    public void generateGenericTest(int size) {
        Set<Integer> indexes = getRandomIndexes(size, indexedIdCache.size());

        List<Long> langUnitIds = indexes.stream().map(i -> indexedIdCache.getIdByIndex(i)).collect(Collectors.toList());
        langUnitsTest = langUnitRepository.findByIds(langUnitIds);

    }

    @Override
    public void generateCustomTest(int size) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.findByUsername(username);
        List<LangUnit> langUnits = new ArrayList<>(user.getLangUnits());
        Set<Integer> indexes = getRandomIndexes(size, langUnits.size());
        List<LangUnit> test = new ArrayList<>();
        for (int index : indexes) {
            test.add(langUnits.get(index));
        }
        langUnitsTest = test;

    }

    private Set<Integer> getRandomIndexes(int size, int range) {
        Set<Integer> indexes = new HashSet<>();
        while (indexes.size() != size) {
            int index = (int)( Math.random()*range );
            indexes.add(index);
        }
        return indexes;
    }
}
