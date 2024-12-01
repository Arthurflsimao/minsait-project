package com.project.resource;

import com.project.entity.Operacao;
import com.project.repository.OperacaoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/operacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OperacaoResource {

    @Inject
    OperacaoRepository operacaoRepository;

    @POST
    public Response create(Operacao operacao) {
        operacaoRepository.persist(operacao);
        return Response.status(Response.Status.CREATED).entity(operacao).build();
    }

    @GET
    public List<Operacao> list() {
        return operacaoRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Operacao operacao = operacaoRepository.findById(id);
        if (operacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(operacao).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (operacaoRepository.deleteById(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Operacao operacao) {
        Operacao existingOperacao = operacaoRepository.findById(id);
        if (existingOperacao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingOperacao.setNome(operacao.getNome());
        existingOperacao.setDescricao(operacao.getDescricao());
        existingOperacao.setCategoria(operacao.getCategoria());
        operacaoRepository.persist(existingOperacao);
        return Response.ok(existingOperacao).build();
    }
}
