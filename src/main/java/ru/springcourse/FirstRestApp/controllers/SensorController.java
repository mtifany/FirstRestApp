package ru.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.FirstRestApp.dto.SensorDto;
import ru.springcourse.FirstRestApp.models.Sensor;
import ru.springcourse.FirstRestApp.services.SensorService;
import ru.springcourse.FirstRestApp.util.MeasurementErrorResponse;
import ru.springcourse.FirstRestApp.util.MeasurementException;
import ru.springcourse.FirstRestApp.util.SensorValidator;
import ru.springcourse.FirstRestApp.util.ErrorsUtil;

import javax.validation.Valid;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService,ModelMapper modelMapper,
                            SensorValidator sensorValidator){
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDto sensorDto,
                                             BindingResult bindingResult) {
       Sensor sensorToAdd = convertToSensor(sensorDto);

       sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()){
            ErrorsUtil.ErrorReturning(bindingResult);
        }
       sensorService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDto sensorDto){

        //          реализация без modelMapper
//        Sensor sensor = new Sensor();
//        sensor.setName(sensorDto.getName());

        return  modelMapper.map(sensorDto, Sensor.class);
    }
}