package com.oleinik.controller;


import com.oleinik.cache.IndexedIdCache;
import com.oleinik.entity.LangUnit;
import com.oleinik.entity.User;
import com.oleinik.service.LangUnitService;
import com.oleinik.service.UserService;
import com.oleinik.util.JsonStatus;
import com.oleinik.util.KendoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class LangUnitController {

    @Autowired
    private LangUnitService langUnitService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndexedIdCache indexedIdCache;


    @RequestMapping(value = "/langUnits", method = RequestMethod.POST)
    public List<LangUnit> getAllLangUnits() {
        return langUnitService.findAll();
    }

    @RequestMapping(value = "/langUnits/create", method = RequestMethod.POST)
    public LangUnit create(LangUnit unit) {
        langUnitService.save(unit);
        indexedIdCache.addId(unit.getId());
        return unit;
    }

    @RequestMapping(value = "/langUnits/update", method = RequestMethod.POST)
    public LangUnit update(LangUnit unit) {
        langUnitService.save(unit);
        return unit;
    }

    @RequestMapping(value = "/langUnits/destroy", method = RequestMethod.POST)
    public LangUnit destroy(LangUnit unit) {
        langUnitService.remove(unit);
        indexedIdCache.removeId(unit.getId());
        return unit;
    }

    @RequestMapping(value = "/langUnits/addBatch", method = RequestMethod.POST)
    public JsonStatus addBatch(String batchStr) {
        List<LangUnit> langUnits;
        try {
            langUnits = langUnitService.parse(batchStr);
        } catch (Exception e) {
            return new JsonStatus(JsonStatus.ERROR, e.getMessage());
        }

        langUnitService.save(langUnits);
        indexedIdCache.addIds(langUnits.stream().map(LangUnit::getId).collect(Collectors.toList()));
        return new JsonStatus(JsonStatus.OK);
    }

    @RequestMapping(value = "/langUnits/kendoData", method = RequestMethod.POST)
    public List<KendoData> findDataByPart(@RequestParam(value="filter[filters][0][value]", required = false) String[] values) {

        List<LangUnit> foundUnits;
        if (values == null)
            foundUnits = getAllLangUnits();
        else
            foundUnits = langUnitService.findByVersionPart(values[0]);

        if (foundUnits == null) return null;

        return foundUnits.stream().map(
                i -> new KendoData(i.getId().toString(), i.getVersionEn() + " = " + i.getVersionRu()
                )
        ).collect(Collectors.toList());
    }

    @RequestMapping(value = "/langUnits/attach", method = RequestMethod.POST)
    public JsonStatus attach(@RequestParam(value="unitIds[]", required = false)List<Long> unitIds, Principal principal) {
        langUnitService.attach(principal.getName(), unitIds);
        return new JsonStatus(JsonStatus.OK);
    }

    @RequestMapping(value = "/langUnits/attach", method = RequestMethod.GET)
    public List<KendoData> getAttachedUnits(Principal principal) {
        Set<LangUnit> units = langUnitService.getAttachedUnits(principal.getName());
        return units.stream().map(
                i -> new KendoData(i.getId().toString(), i.getVersionEn() + " = " + i.getVersionRu()
                )
        ).collect(Collectors.toList());
    }

}
