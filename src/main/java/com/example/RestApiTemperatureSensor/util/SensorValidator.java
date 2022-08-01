package com.example.RestApiTemperatureSensor.util;

import com.example.RestApiTemperatureSensor.models.Sensor;
import com.example.RestApiTemperatureSensor.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        if (sensorsService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "This name of sensor already exists!");
        }

    }
}
