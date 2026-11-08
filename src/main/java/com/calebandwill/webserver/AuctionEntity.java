package com.calebandwill.webserver;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AuctionEntity {

    @Id
    private int bidId;
    private String itemName;
    private String itemDesc;
    private String sellerName;


    public int getBidId() {
        return bidId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getSellerName() {
        return sellerName;
    }

    public AuctionEntity(int bidId, String itemName, String itemDesc, String sellerName){

        this.bidId = bidId;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.sellerName = sellerName;

    }






}
