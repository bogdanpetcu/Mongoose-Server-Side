/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.hackzurich.mongoose.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ro.hackzurich.mongoose.boundary.Constants;
import ro.hackzurich.mongoose.entity.Challenge;
import ro.hackzurich.mongoose.entity.Notification;
import ro.hackzurich.mongoose.entity.User;

/**
 *
 * @author bogdanpetcu
 */
@Stateless
@Path("ro.hackzurich.mongoose.entity.notification")
public class NotificationFacadeREST extends AbstractFacade<Notification> {

    @PersistenceContext(unitName = "ro.hackzurich_mongoose_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public NotificationFacadeREST() {
        super(Notification.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Notification entity) {
        Object id = em.createNamedQuery("Notification.getNextID").getSingleResult();
        if (id != null) {
            entity.setId((long) id);
        } else {
            entity.setId((long) 0);
        }
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Notification entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Notification find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Notification> findAll() {
        return super.findAll();
    }

    @GET
    @Path("/user/{user}")
    @Produces({"application/json"})
    public List<Notification> allNotifications(@PathParam("user") String id) {
        List<Notification> all;
        all = em.createNamedQuery("Notification.findByToId")
                .setParameter("toId", id)
                .getResultList();
        return all;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Notification> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
