package dev.lucas.desafiocdc.configurations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    
    List<String> globalErrors = new ArrayList<String>();
    Map<String, String> fieldErrors = new HashMap<String, String>();

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public List<String> getGlobalErrors() {
        return globalErrors;
    }

    public void addErrors(String errorMessage) {
        globalErrors.add(errorMessage);
    }


    public void addFieldError(String field, String message) {
        fieldErrors.put(field, message);
    }

}
