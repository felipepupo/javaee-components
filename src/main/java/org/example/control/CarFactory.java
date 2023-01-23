package org.example.control;

import org.example.entity.Car;
import org.example.entity.Color;
import org.example.entity.Specification;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Random;
import java.util.UUID;

@Dependent
public class CarFactory {
    @Inject
    @Diesel
    Color defaultCarColor;

    public Car createCar(Specification specification) {
        if (new Random().nextBoolean())
            throw new CarCreationException("could not create car");
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
        car.setEngineType(specification.getEngineType());
        return car;
    }
}