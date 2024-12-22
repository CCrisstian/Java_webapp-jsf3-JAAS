package org.cristian.webapp.jsf3.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.cristian.webapp.jsf3.entities.Producto;

import java.util.List;

@RequestScoped
public class ProductoRepositoryImpl implements ProductoRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<Producto> listar() {
        return em.createQuery("SELECT p FROM Producto AS p LEFT OUTER JOIN FETCH p.categoria", Producto.class).getResultList();
    }

    @Override
    public void guardar(Producto producto) {
        if (producto.getId() != null && producto.getId() > 0) {
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }

    @Override
    public Producto porId(Long id) {
//        return em.find(Producto.class, id);
        return em.createQuery("SELECT p FROM Producto AS p LEFT OUTER JOIN FETCH p.categoria WHERE p.id=:id", Producto.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = porId(id);
        em.remove(producto);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return em.createQuery("SELECT p FROM Producto AS p LEFT OUTER JOIN FETCH p.categoria WHERE p.nombre LIKE :nombre", Producto.class)
                .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
    }
}
