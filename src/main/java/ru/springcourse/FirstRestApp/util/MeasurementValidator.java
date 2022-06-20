package ru.springcourse.FirstRestApp.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.springcourse.FirstRestApp.models.Measurement;
import ru.springcourse.FirstRestApp.models.Sensor;
import ru.springcourse.FirstRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService){
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        Sensor sensor = measurement.getSensor();

        if(sensor == null){
            return;
        }
       if(sensorService.findByName(sensor.getName()).isEmpty())
           errors.rejectValue("sensor", "There is no sensor with this name is database");

    }
}
