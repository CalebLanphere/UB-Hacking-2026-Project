package com.calebandwill.webserver.Repository;

import com.calebandwill.webserver.TextObfuscationEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends CrudRepository<TextObfuscationEntity, Integer> {

    @Query(value = "SELECT * FROM auctions WHERE bidid = ?1", nativeQuery=true)
    public List<Object[]> getAuction(Integer id);

    @Query(value = "SELECT * FROM auctions", nativeQuery=true)
    public List<Object[]> getAuctions();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO auctions (bidid, itemname, itemdescription, sellername) VALUES (?1, ?2, ?3, ?4)", nativeQuery=true)
    public void createAuction(Integer id, String itemName, String itemDesc, String sellerName);
//
//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE auction set values = CAST(?2 AS jsonb) WHERE id = ?1", nativeQuery=true)
//    public void updateTextObfuscationMap(Integer id, String jsonObjectString);
}
