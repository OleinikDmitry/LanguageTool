package com.oleinik.repository;

import com.oleinik.entity.LangUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LangUnitRepository extends JpaRepository<LangUnit, Long> {

    List<LangUnit> findByVersionRuIgnoreCaseContaining(String versionRu);
    List<LangUnit> findByVersionEnIgnoreCaseContaining(String versionEn);

    @Query("SELECT u.id FROM LangUnit u")
    List<Long> findAllIds();

    @Query("select u from LangUnit u where u.id in :ids")
    List<LangUnit> findByIds(@Param("ids") List<Long> ids);

}
