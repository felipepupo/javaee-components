package org.example.boundary;

import org.example.control.CarFactory;
import org.example.control.CarRepository;
import org.example.entity.Car;
import org.example.entity.CarCreated;
import org.example.entity.Specification;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarManufacturer {
    @Inject
    CarFactory carFactory;
    @Inject
    CarRepository carRepository;
    @Inject
    Event<CarCreated> carCreatedEvent;

    public Car manufactureCar (Specification specification){
        Car car = carFactory.createCar(specification);
        // carRepository.store(car);
        carCreatedEvent.fire(new CarCreated(car.getIdentifier()));
        return car;
    }

    public List<Car> retrieveCars(){
        return carRepository.loadCars();
    }

    public Car retrieveCar(String identifier) {
        return CarRepository.loadCar();
    }
}
