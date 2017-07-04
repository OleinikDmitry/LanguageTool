package com.oleinik.cache;

import com.oleinik.exception.IndexesNotInitException;
import com.oleinik.repository.LangUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class IndexedIdCacheImpl implements IndexedIdCache{

    @Autowired
    private LangUnitRepository langUnitRepository;

    private List<Long> indices;
    private boolean hasInit = false;

    @Override
    public void sync() {
        /*TypedQuery<Long> query = em.createQuery("SELECT u.id FROM LangUnit u", Long.class);
        indices = query.getResultList();*/
        indices = langUnitRepository.findAllIds();
        hasInit = true;
    }

    @Override
    public long getIdByIndex(int index) {
        checkInit();
        return indices.get(index);

    }

    @Override
    public void addId(Long id) {
        checkInit();
        indices.add(id);
    }

    @Override
    public void addIds(List<Long> ids) {
        checkInit();
        indices.addAll(ids);
    }

    @Override
    public void removeId(Long id) {
        checkInit();
        indices.remove(id);
    }

    @Override
    public int size() {
        checkInit();
        return indices.size();
    }

    private void checkInit() {
        if (! hasInit) throw new IndexesNotInitException("Indices has not been initialized!");
    }
}
