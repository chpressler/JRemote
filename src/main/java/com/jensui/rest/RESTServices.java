package com.jensui.rest;

import com.jensui.DeviceHandler;
import com.jensui.interfaces.IDevice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by christian on 11/6/16.
 */
@Path("/remote")
public class RESTServices {

    @POST
    @Path("/control")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response control(RCCommand cmd) {
        System.out.println("device id: " + cmd.getDeviceId() + " - command: " + cmd.getCommand());
        return Response.status(201).entity(cmd + " received").build();
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
    @Path("/wsclosed")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String webSocketClosed() {

        return "client notified";
    }

    @GET
    @Path("/shutdown")
    public String shutdown() {
        System.exit(0);
        return "";
    }

}
