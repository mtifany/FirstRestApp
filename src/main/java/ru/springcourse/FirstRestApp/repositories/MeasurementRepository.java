package ru.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.springcourse.FirstRestApp.models.Measurement;



@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {


}
