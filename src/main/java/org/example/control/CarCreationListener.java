package org.example.control;

import org.example.entity.CarCreated;

import javax.enterprise.event.Observes;

public class CarCreationListener {

    public void onCarCreation(@Observes CarCreated carCreated){
        // ...
        System.out.println("new car creatd with id " + carCreated.getIdentifier());
    }
}
