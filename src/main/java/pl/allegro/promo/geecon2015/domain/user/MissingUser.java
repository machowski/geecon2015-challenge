package pl.allegro.promo.geecon2015.domain.user;

import java.util.UUID;

public class MissingUser implements User {
    public static final String FAILED = "<failed>";
    private final UUID id;

    public MissingUser(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return FAILED;
    }
}
