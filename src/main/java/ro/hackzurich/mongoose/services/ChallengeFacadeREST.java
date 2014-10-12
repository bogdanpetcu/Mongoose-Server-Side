/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.hackzurich.mongoose.services;

import java.util.Date;
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
import ro.hackzurich.mongoose.entity.gcmNotification;

/**
 *
 * @author bogdanpetcu
 */
@Stateless
@Path("ro.hackzurich.mongoose.entity.challenge")
public class ChallengeFacadeREST extends AbstractFacade<Challenge> {

    @PersistenceContext(unitName = "ro.hackzurich_mongoose_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ChallengeFacadeREST() {
        super(Challenge.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Challenge entity) {
        Object id = em.createNamedQuery("Challenge.getNextID").getSingleResult();
        this.notifyPhone(entity);
        if (id != null) {
            entity.setId((long) id);
        } else {
            entity.setId((long) 0);
        }
        //    this.notifyUser(entity.getToId());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Long id, Challenge entity) {
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
    public Challenge find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Challenge> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{user_to}/completed")
    @Produces({"application/json"})
    public List<Challenge> allCompleted(@PathParam("user_to") String id) {
        List<Challenge> all;
        all = em.createNamedQuery("Challenge.findByToIdAndByStatus")
                .setParameter("toId", id).setParameter("status", 0)
                .getResultList();
        return all;
    }

    @GET
    @Path("{user_to}/pending")
    @Produces({"application/json"})
    public List<Challenge> allPending(@PathParam("user_to") String id) {
        List<Challenge> all;
        all = em.createNamedQuery("Challenge.findByToIdAndByStatus")
                .setParameter("toId", id).setParameter("status", 1)
                .getResultList();
        return all;
    }

    @GET
    @Path("{user_to}/dismissed")
    @Produces({"application/json"})
    public List<Challenge> allDissmised(@PathParam("user_to") String id) {
        List<Challenge> all;
        all = em.createNamedQuery("Challenge.findByToIdAndByStatus")
                .setParameter("toId", id).setParameter("status", 2)
                .getResultList();
        return all;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Challenge> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    private void notifyPhone(Challenge challenge) {
        User notified;
        notified = (User) em.createNamedQuery("User.findByFbId")
                .setParameter("fbId", challenge.getToId())
                .getSingleResult();
        String registration_id = notified.getGcmId();
        Notification notif = new Notification((long)0, challenge.getFromId(), challenge.getToId(), 0, new Date());
        Client client = ClientBuilder.newClient();
        WebTarget target
                = client.target(Constants.GCM_SERVER);
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "key=" + Constants.GCM_API_KEY)
                .post(Entity.json(new gcmNotification(notif, registration_id)));
    }
}
