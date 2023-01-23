package org.example.control;
import org.example.entity.Car;

import java.util.Arrays;

import javax.enterprise.context.Dependent;

@Dependent
public class CarRepository {
    public void loadCar() {
        return;
    }

    public Object store(Car car){
        return null;
    }

    public List<Car> loadCars() {
        return Arrays.asList();
    }
}
