package com.sqli.stories.helpers.factory;

import com.sqli.stories.helpers.payload.SprintExistingKey;

public class SprintExistingKeyFactory {
    private SprintExistingKeyFactory(){}
    public static SprintExistingKey createSprintExistingKey(Long numero){
        return new SprintExistingKey(numero);
    }
}
