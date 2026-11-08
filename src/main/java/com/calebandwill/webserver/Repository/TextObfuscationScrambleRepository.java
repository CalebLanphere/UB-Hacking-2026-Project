package com.calebandwill.webserver.Repository;

import com.calebandwill.webserver.TextObfuscationEntity;
import com.calebandwill.webserver.TextScrambleMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TextObfuscationScrambleRepository extends CrudRepository<Integer, TextObfuscationEntity> {

    @Query(value = "SELECT * FROM text_obfuscation WHERE id = ?1", nativeQuery=true)
    public List<Object[]> getTextObfuscationMap(Integer id);
}
