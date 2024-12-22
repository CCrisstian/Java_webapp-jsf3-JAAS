package org.cristian.webapp.jsf3.services;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.cristian.webapp.jsf3.entities.Categoria;
import org.cristian.webapp.jsf3.entities.Producto;
import org.cristian.webapp.jsf3.repositories.CrudRepository;
import org.cristian.webapp.jsf3.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Stateless
@DeclareRoles({"USER", "ADMIN"})
public class ProductoServiceImpl implements ProductoService {

    @Inject
    private ProductoRepository repository;

    @Inject
    private CrudRepository<Categoria> categoriaRepository;

    @Override
    @PermitAll
    public List<Producto> listar() {
        return repository.listar();
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional<Producto> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void guardar(Producto producto) {
        repository.guardar(producto);
    }

    @Override

    @RolesAllowed({"ADMIN"})
    public void eliminar(Long id) {
        repository.eliminar(id);
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.ofNullable(categoriaRepository.porId(id));
    }

    @Override
    @RolesAllowed({"USER","ADMIN"})
    public List<Producto> buscarPorNombre(String nombre) {
        return repository.buscarPorNombre(nombre);
    }
}
