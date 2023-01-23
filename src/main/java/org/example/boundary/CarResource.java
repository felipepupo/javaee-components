package org.example.boundary;
import java.net.URI;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.stream.JsonCollectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.example.entity.Car;
import org.example.entity.Specification;

@Path("cars")
public class CarResource {

    @Inject
    CarManufacturer carManufacturer;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray retrieveCars(){
        return carManufacturer.retrieveCars()
                .stream()
                .map( c -> Json.createObjectBuilder()
                        .add("color", c.getColor().name())
                        .add("engine", c.getEngineType().name())
                        .add("id", c.getIdentifier())
                        .add("hello", "value")
                        .build()
                )
                .collect(JsonCollectors.toJsonArray());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(@Valid @NotNull Specification specification){
        Car car = carManufacturer.manufactureCar(specification);

        URI uri = uriInfo.getBaseUriBuilder()
                .path(CarResource.class)
                .path(CarResource.class, "retrieveCar")
                .build(car.getIdentifier());

        return Response.status(Response.Status.CREATED)
                .header(HttpHeaders.LOCATION, uri)
                .build();
    }

    @GET
    @Path("{id}")
    public Car retrieveCar(@PathParam("id") String identifier){
        return carManufacturer.retrieveCar(identifier);
    }
}
