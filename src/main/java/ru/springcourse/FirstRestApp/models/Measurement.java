package ru.springcourse.FirstRestApp.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "value")
    @NotNull
    @Min(value= -100, message = "too low temperature!")
    @Max(value= 100, message = "too low temperature!")
    private Float value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Sensor getSensor() { return sensor; }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", sensor='" + sensor + '\'' +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                '}';
    }
}
