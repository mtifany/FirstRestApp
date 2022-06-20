package ru.springcourse.FirstRestApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.FirstRestApp.models.Measurement;
import ru.springcourse.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;


    public MeasurementService(MeasurementRepository measurmentRepository, SensorService sensorService) {
        this.measurementRepository = measurmentRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }


    private void enrichMeasurement(Measurement measurement){


        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
       measurement.setCreatedAt(LocalDateTime.now());
    }

    public String findRainyDays() {
      int n = (int)  measurementRepository.findAll().stream().filter(Measurement::isRaining).count();
        return "Number of rainy days is " + n;
   }

}
