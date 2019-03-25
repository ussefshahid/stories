package com.sqli.stories.helpers.payload;

public class MemberIdentityAvailability {
    private Boolean available;

    public MemberIdentityAvailability(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
