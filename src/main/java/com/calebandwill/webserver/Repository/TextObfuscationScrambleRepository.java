package com.calebandwill.webserver.Repository;

import com.calebandwill.webserver.TextObfuscationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextObfuscationScrambleRepository extends CrudRepository<TextObfuscationEntity, Integer> {

    @Query(value = "SELECT * FROM text_obfuscation WHERE id = ?1", nativeQuery=true)
    public List<Object[]> getTextObfuscationMap(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO text_obfuscation (id, values) VALUES (0, ?2)", nativeQuery=true)
    public List<Object[]> createTextObfuscationMap(Integer id, String jsonObjectString);

    @Transactional
    @Modifying
    @Query(value = "UPDATE text_obfuscation set values = ?2 WHERE id = ?1", nativeQuery=true)
    public List<Object[]> updateTextObfuscationMap(Integer id, String jsonObjectString);
}
