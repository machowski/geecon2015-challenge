package pl.allegro.promo.geecon2015.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DefaultUser implements User {
    
    private final UUID id;
    
    private final String name;
    
    @JsonCreator
    public DefaultUser(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
