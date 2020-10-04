package mx.com.gm.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;
import mx.com.gm.domain.Persona;

@Stateless
public class PersonaDaoImpl implements PersonaDAO{
    
    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;
    @Override
    public List<Persona> listarPersonas() {
        return em.createNamedQuery("Persona.encontrarTodasPersonas").getResultList();
    }

    @Override
    public Persona encontrarPersona(Persona p) {
        return em.find(Persona.class, p.getIdPersona());
    }

    @Override
    public void insertarPersona(Persona p) {
        em.persist(p);
        //Flush para insertar directamente en la base de datos
        em.flush();
    }

    @Override
    public void actualizarPersona(Persona p) {
        em.merge(p);
    }

    @Override
    public void eliminarPersona(Persona p) {
        em.remove(em.merge(p));
    }
    
}
