package com.calebandwill.webserver.Services;

import com.calebandwill.webserver.AuctionEntity;
import com.calebandwill.webserver.Repository.AuctionRepository;
import com.calebandwill.webserver.Repository.TextObfuscationScrambleRepository;
import com.calebandwill.webserver.TextObfuscation.TextObfuscationService;
import com.calebandwill.webserver.TextScrambleMapping;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TextObfuscationScrambleService {
    private TextObfuscationScrambleRepository repository;
    private AuctionRepository auctionRepository;

    public TextObfuscationScrambleService(TextObfuscationScrambleRepository repository, AuctionRepository auctionRepository) {
        this.repository = repository;
        this.auctionRepository = auctionRepository;
    }

    public HashMap<Character, Character> getMapOfCharacters() {
        List<Object[]> entry = repository.getTextObfuscationMap(0);
        HashMap<Character, Character> returnMap = new HashMap<>();

        if(entry.isEmpty()) {
            try {
                placeMapInDb(TextObfuscationService.generateFont("MontserratBold-DOWZd.ttf"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(entry.size() > 1) {
            Logger.getGlobal().warning("shits fucked but differently");
        }

        JsonObject object = Json.createReader(new StringReader((String) entry.get(0)[1])).readObject();

        for(String charChangedTo : object.keySet()) {
            returnMap.put(charChangedTo.toCharArray()[0], object.getString(charChangedTo).toCharArray()[0]);
        }

        return returnMap;
    }

    public void placeMapInDb(JsonObject json) {
        if(repository.getTextObfuscationMap(0) != null) {
            repository.updateTextObfuscationMap(0, json.toString());
        } else {
            repository.createTextObfuscationMap(0, json.toString());
        }

        TextScrambleMapping.mapOfCharactersScrambled = getMapOfCharacters();
    }

    public void createAuction(int bidId, String itemName, String itemDesc, String sellerName) {
        auctionRepository.createAuction(bidId, itemName, itemDesc, sellerName);
    }

    public AuctionEntity getAuction(int bidId) {
        List<Object[]> auctionObj = auctionRepository.getAuction(bidId);
        Object[] obj = auctionObj.get(0);

        AuctionEntity entity = new AuctionEntity((int) obj[0], (String) obj[1], (String) obj[2], (String) obj[3]);

        return entity;
    }
}
