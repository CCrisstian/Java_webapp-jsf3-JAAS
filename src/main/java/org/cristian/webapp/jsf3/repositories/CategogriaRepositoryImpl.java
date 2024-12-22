package org.cristian.webapp.jsf3.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.cristian.webapp.jsf3.entities.Categoria;

import java.util.List;

@RequestScoped
public class CategogriaRepositoryImpl implements CrudRepository<Categoria>{

    @Inject
    private EntityManager em;

    @Override
    public List<Categoria> listar() {
        return em.createQuery("SELECT c FROM Categoria AS c", Categoria.class).getResultList();
    }

    @Override
    public Categoria porId(Long id) {
        return em.find(Categoria.class, id);
    }

    @Override
    public void guardar(Categoria categoria) {
        if (categoria.getId() != null && categoria.getId() > 0) {
            em.merge(categoria);
        } else {
            em.persist(categoria);
        }
    }

    @Override
    public void eliminar(Long id) {
        em.remove(porId(id));
    }
}
