/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entities.Message;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jitse
 */
import interceptors.ProfanityInterceptor;
import java.util.Collections;
import javax.ejb.Schedule;

@Stateless
public class MessageEJB {

    @PersistenceContext(unitName = "ProjectDistrubutedPU")
    private EntityManager entityManager;

    public List<Message> getMessages() {
        TypedQuery<Message> query = entityManager.createNamedQuery("Message.findAll", Message.class);
        List<Message> l = query.getResultList();
        Collections.reverse(l);
        l=l.subList(0, Math.min(l.size(),24));
        return l;

    }
    
    @Schedule(second = "*/10", minute = "*", hour = "*", persistent = false)
    public void stillAwake() {
        System.out.println("I am still awake");
    } 

    @Interceptors(ProfanityInterceptor.class)
    public Message addNew(Message m) {
        entityManager.persist(m);
        return m;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
