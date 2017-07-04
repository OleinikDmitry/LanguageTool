package com.oleinik.service;

import com.oleinik.entity.LangUnit;

import java.util.List;
import java.util.Set;


public interface LangUnitService {

    List<LangUnit> findAll();

    void save(LangUnit langUnit);

    void save(List<LangUnit> langUnits);

    void remove(LangUnit langUnit);

    List<LangUnit> parse(String batchStr);

    List<LangUnit> findByVersionPart(String part);

    List<LangUnit> findByIds(List<Long> ids);

    void attach(String userName, List<Long> unitIds);

    Set<LangUnit> getAttachedUnits(String userName);

}
