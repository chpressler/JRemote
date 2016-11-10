package com.jensui;

import com.jensui.interfaces.IDevice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by christian on 11/6/16.
 */
@Path("/remote")
public class RESTServices {

    @GET
    @Path("/control")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String control() {
        return "";
    }

    @GET
    @Path("/turnOn/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String turnOn(@PathParam("id") String id) {
        for(IDevice d : DeviceHandler.getInstance().getDevices()) {
            if (d.getId().equals(id)) {
                d.turnOn();
                return "device with id: " + id + " turned on.";
            }
        }
        return "device with id: " + id + " not found.";
    }

    @GET
    @Path("/turnOff/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String turnOff(@PathParam("id") String id) {
        for(IDevice d : DeviceHandler.getInstance().getDevices()) {
            if (d.getId().equals(id)) {
                d.turnOff();
                return "device with id: " + id + " turned off.";
            }
        }
        return "device with id: " + id + " not found.";
    }

    @GET
    @Path("/shutdown")
    public String shutdown() {
        System.exit(0);
        return "";
    }

}
