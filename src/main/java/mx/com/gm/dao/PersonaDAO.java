package mx.com.gm.dao;

import java.util.List;
import mx.com.gm.domain.Persona;

public interface PersonaDAO {
    public List<Persona> listarPersonas();
    
    public Persona encontrarPersona(Persona p);
    
    public void insertarPersona(Persona p);
    
    public void actualizarPersona(Persona p);
    
    public void eliminarPersona(Persona p);
}
