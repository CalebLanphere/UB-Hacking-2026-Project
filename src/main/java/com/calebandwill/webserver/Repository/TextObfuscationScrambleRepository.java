package com.calebandwill.webserver.Repository;

import com.calebandwill.webserver.TextObfuscationEntity;
import jakarta.json.Json;
import jakarta.json.JsonObject;
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
    @Query(value = "INSERT INTO text_obfuscation (id, values) VALUES (0, CAST(?2 AS jsonb))", nativeQuery=true)
    public void createTextObfuscationMap(Integer id, String jsonObjectString);

    @Transactional
    @Modifying
    @Query(value = "UPDATE text_obfuscation set values = CAST(?2 AS jsonb) WHERE id = ?1", nativeQuery=true)
    public void updateTextObfuscationMap(Integer id, String jsonObjectString);
}
