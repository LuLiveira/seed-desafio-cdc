package dev.lucas.desafiocdc.states.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.lucas.desafiocdc.states.domain.State;

public record StateResponse (String name,
                               @JsonProperty("country") StateCountryResponse stateCountryResponse){

    public static StateResponse from(State state) {
        return new StateResponse(state.getName(),
                new StateCountryResponse(state.getCountry().getName()));
    }
}
