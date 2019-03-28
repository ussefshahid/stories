package com.sqli.stories.helpers.payload;

public class SprintIdentityAvailability {
    private boolean available;

    public SprintIdentityAvailability(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
