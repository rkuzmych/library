package com.rkuzmych.library.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class EntityValidationService {
    public boolean checkEmptyFields(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return true;
        }
        return false;
    }
}
