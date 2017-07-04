package com.oleinik.service;

import com.oleinik.entity.LangUnit;

public interface TestService {

    LangUnit getUnitFromTest(int index);

    void generateGenericTest(int size);

    void generateCustomTest(int size);

}
