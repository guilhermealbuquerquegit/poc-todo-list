package api;

import model.Todo;
import service.TodoService;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.Response.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CONFLICT;


@Path("/todos")
@Produces(APPLICATION_JSON)
public class TodoResource {

    @Resource

    @Context
    private UriInfo uriInfo;

    @Inject
    private TodoService service;

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @POST
    public Response save(@Valid Todo todo) {
        return created(uriInfo.getAbsolutePathBuilder().path(service.save(todo).getId().toString()).build()).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") @Min(value = 1, message = "O valor do id deve ser no mínimo 1.") Long id, Todo todo) {
        if (!id.equals(todo.getId())) {
            return status(CONFLICT).build();
        }
        service.update(todo);
        return noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") @Min(value = 1, message = "O valor do id deve ser no mínimo 1")  Long id) {
        service.remove(id);
        return noContent().build();
    }

}