package ru.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.FirstRestApp.models.Measurement;
import ru.springcourse.FirstRestApp.services.MeasurementService;
import ru.springcourse.FirstRestApp.util.MeasurementErrorResponse;
import ru.springcourse.FirstRestApp.dto.MeasurementDto;
import ru.springcourse.FirstRestApp.util.MeasurementException;
import ru.springcourse.FirstRestApp.util.MeasurementValidator;
import ru.springcourse.FirstRestApp.util.ErrorsUtil;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody над каждым методом
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 ModelMapper modelMapper,
                                 MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<Measurement> getMeasurement() {
        return measurementService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDto measurementDto,
                                             BindingResult bindingResult) {
        Measurement measurementToAdd = convertToMeasurement(measurementDto);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            ErrorsUtil.ErrorReturning(bindingResult);
        }
        measurementService.save(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDto measurementDto) {
        //          реализация без modelMapper

//             Measurement measurement = new Measurement();
//        measurement.setValue(measurementDto.getValue());
//        measurement.setRaining(measurementDto.getRaining());
//        System.out.println(measurementDto.getSensor());
//        measurement.setSensor(measurement.getSensor());
        return modelMapper.map(measurementDto, Measurement.class);
    }

    @GetMapping("/rainyDaysCount")
    public String getRainyDays() {
        return measurementService.findRainyDays();
    }
}