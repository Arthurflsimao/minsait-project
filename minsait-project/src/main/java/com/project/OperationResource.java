package com.project;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperationResource {

    @GET
    public Response listAll() {
        List<Operation> operations = Operation.listAll();
        return Response.ok(operations).build();
    }

    @POST
    @Transactional
    public Response add(Operation operation) {
        if (operation == null || operation.getName() == null || operation.getDescription() == null || operation.getCategory() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid operation data").build();
        }
        operation.persist();
        return Response.status(Response.Status.CREATED).entity(operation).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Operation operation = Operation.findById(id);
        if (operation == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Operation not found").build();
        }
        return Response.ok(operation).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Operation operation) {
        Operation entity = Operation.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Operation not found").build();
        }
        if (operation == null || operation.getName() == null || operation.getDescription() == null || operation.getCategory() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid operation data").build();
        }
        entity.setName(operation.getName());
        entity.setDescription(operation.getDescription());
        entity.setCategory(operation.getCategory());
        entity.persist();
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Operation.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).entity("Operation not found").build();
        }
        return Response.noContent().build();
    }
}
