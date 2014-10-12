/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.hackzurich.mongoose.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ro.hackzurich.mongoose.entity.Challenge;

/**
 *
 * @author bogdanpetcu
 */
@Stateless
public class ChallengeFacade extends AbstractFacade<Challenge> {
    @PersistenceContext(unitName = "ro.hackzurich_mongoose_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChallengeFacade() {
        super(Challenge.class);
    }
    
}
