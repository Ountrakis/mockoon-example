package org.acme.service;


import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.client.CourseRestClient;
import org.acme.client.StudentRestClient;
import org.acme.model.Student;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class Service {

    @Inject
    @RestClient
    CourseRestClient courseRestClient;

    @Inject
    @RestClient
    StudentRestClient studentRestClient;

    public Uni<Student> getStudent() {
        return studentRestClient.getStudent()
                .onItem()
                .transformToUni(student ->
                        courseRestClient.getCourse()
                                .onItem()
                                .transform(course -> {
                                            student.setCourse(course);
                                            Log.info("Got Student");
                                            return student;
                                        })
                )
                .onFailure()
                .recoverWithUni(throwable -> {
                    throwable.printStackTrace();
                    return Uni.createFrom().failure(new RuntimeException("Got Problem with Uni"));
                });
    }
}
