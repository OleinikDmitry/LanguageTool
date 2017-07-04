package com.oleinik.service;


import com.oleinik.entity.LangUnit;
import com.oleinik.entity.User;
import com.oleinik.repository.LangUnitRepository;
import com.oleinik.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LangUnitServiceImpl implements LangUnitService {

    @Autowired
    private LangUnitRepository langUnitRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<LangUnit> findAll() {
        return langUnitRepository.findAll();
    }

    @Override
    public void save(LangUnit langUnit) {
        langUnitRepository.save(langUnit);
    }

    public void save(List<LangUnit> langUnits) {
        langUnitRepository.save(langUnits);
    }

    @Override
    public void remove(LangUnit langUnit) {
        langUnitRepository.delete(langUnit);
    }

    @Override
    public List<LangUnit> parse(String batchStr) {
        List<LangUnit> langUnits = new ArrayList<>();

        String lines[] = batchStr.split("\\r\\n|\\n|\\r");
        for (String line: lines) {
            if (line == null || line.trim().isEmpty()) continue;

            String[] versions = line.split("=");
            langUnits.add(new LangUnit(versions[0].trim(), versions[1].trim()));
        }
        return langUnits;
    }

    @Override
    public List<LangUnit> findByVersionPart(String part) {
        if (StringUtils.isEnglishFirstChar(part))
            return langUnitRepository.findByVersionEnIgnoreCaseContaining(part);
        else
            return langUnitRepository.findByVersionRuIgnoreCaseContaining(part);
    }

    @Override
    public List<LangUnit> findByIds(List<Long> ids) {
        return langUnitRepository.findAll(ids);
    }

    @Override
    public void attach(String userName, List<Long> unitIds) {
        User user = userService.findByUsername(userName);
        user.setLangUnits(findByIds(unitIds));
        userService.save(user);
    }

    @Override
    public Set<LangUnit> getAttachedUnits(String userName) {
        User user = userService.findByUsername(userName);
        return user.getLangUnits();
    }
}
