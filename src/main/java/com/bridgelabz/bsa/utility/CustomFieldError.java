package com.bridgelabz.bsa.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomFieldError {

    private String fieldName;
    private String errorMessage;

    public static CustomFieldError create(String fieldName, String errorMessage) {
        CustomFieldError customFieldError = new CustomFieldError();
        customFieldError.setFieldName(fieldName);
        customFieldError.setErrorMessage(errorMessage);
        return customFieldError;
    }
}
