package org.example.control;

import org.example.entity.Color;

import javax.enterprise.inject.Produces;

public class DefaultColorExposed {

    @Produces
    @Diesel
    public Color exposeDefaultColor() {
        return Color.RED;
    }
}
