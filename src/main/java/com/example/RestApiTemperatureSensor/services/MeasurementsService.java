package com.example.RestApiTemperatureSensor.services;

import com.example.RestApiTemperatureSensor.models.Measurement;
import com.example.RestApiTemperatureSensor.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll (){
        return measurementsRepository.findAll();
    }
    @Transactional
    public void save(Measurement measurement){
        measurement.setMeasurementDateTime(LocalDateTime.now());
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurementsRepository.save(measurement);
    }
}
