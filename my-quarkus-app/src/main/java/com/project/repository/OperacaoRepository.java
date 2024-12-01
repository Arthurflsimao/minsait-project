package com.project.repository;

import com.project.entity.Operacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperacaoRepository implements PanacheRepository<Operacao> {
    // Métodos para operações de CRUD podem ser adicionados aqui
}
