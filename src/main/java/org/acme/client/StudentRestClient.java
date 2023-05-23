package org.acme.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.model.Student;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/student")
@RegisterRestClient(configKey = "StudentRestClient")
public interface StudentRestClient {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Student> getStudent();
}
