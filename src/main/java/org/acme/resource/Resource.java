package org.acme.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;
import org.acme.model.Student;
import org.acme.service.Service;

@Path("/getStudent")
@Provider
public class Resource  {
    @Inject
    Service service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Student> hello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return service.getStudent();
    }

}
