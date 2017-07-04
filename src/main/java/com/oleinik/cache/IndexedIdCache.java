package com.oleinik.cache;

import java.util.List;

public interface IndexedIdCache {

    void sync();

    long getIdByIndex(int index);

    void addId(Long id);

    void addIds(List<Long> ids);

    void removeId(Long id);

    int size();

}
