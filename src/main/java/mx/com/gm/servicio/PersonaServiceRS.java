package mx.com.gm.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import mx.com.gm.dao.PersonaDAO;
import mx.com.gm.domain.Persona;

@Stateless
@Path("/personas")
public class PersonaServiceRS {

    @Inject
    private PersonaDAO personaDao;

    @GET
    //Produces porque va a regresar datos de tipo json
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersons() {
        List<Persona> personas = personaDao.listarPersonas();
        System.out.println("personas = " + personas);
        return personas;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}") //hace referencia al path siguiente /personas/id/
    public Persona buscarPersonaPorId(@PathParam("id") int id) {
        Persona p = personaDao.encontrarPersona(new Persona(id));
        System.out.println("p buscada = " + p);
        return p;
    }

    @POST
    //Consumes porque va a consumir informacion
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona p) {
        personaDao.insertarPersona(p);
        System.out.println("p agregada = " + p);
        return p;
    }

    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        Persona persona = personaDao.encontrarPersona(new Persona(id));
        if(persona != null){
            personaDao.actualizarPersona(personaModificada);
            System.out.println("persona = " + personaModificada);
            return Response.ok().entity(personaModificada).build();
        }else{
            return Response.status(Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id") int id){
        personaDao.eliminarPersona(new Persona(id));
        System.out.println("Persona eliminada con el id: "+id);
        return Response.ok().build();
    }

}
