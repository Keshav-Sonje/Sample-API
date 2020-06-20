package resources;


import com.codahale.metrics.annotation.ResponseMetered;
import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import entity.StudentEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import manager.StudentManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @NonNull
    private final StudentManager studentManager;
    @Inject
    public UserResource(StudentManager studentManager){
        this.studentManager = studentManager;
    }

    @POST
    @Timed(name = "Timed.createStudent")
    @ResponseMetered(name = "ResponseMetered.createStudent")
    @Path("add/student")
    public String createStudent(StudentEntity studentEntity){
        return studentManager.create(studentEntity);
    }

    @GET
    @Path("get/student/{id}")
    public StudentEntity getStudent(@PathParam("id") final int id){
        return studentManager.get(id);
    }

    @GET
    @Path("get/allStudents")
    public Response getAllStudents(){
        List<StudentEntity> stu = studentManager.getAll();
        return Response.ok(stu).build();
    }

}
