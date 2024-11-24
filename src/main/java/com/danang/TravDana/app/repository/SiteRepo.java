package com.danang.TravDana.app.repository;


import com.danang.TravDana.app.entity.SiteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepo extends JpaRepository<SiteEntity, Integer>{
//    @Query(value = "SELECT * FROM site WHERE id = :cid", nativeQuery = true)
//    Page<SiteEntity> findBySiteId(int cid, Pageable pageable);


        @Query("SELECT s FROM SiteEntity s LEFT JOIN FETCH s.images WHERE s.id = :cid")
        Page<SiteEntity> findByIdWithImages(@Param("cid") int cid , Pageable pageable);


}
