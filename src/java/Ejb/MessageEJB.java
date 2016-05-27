/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entities.Message;
import Entities.UserAccount;
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
import java.util.ArrayList;
import java.util.Collections;

@Stateless
public class MessageEJB {

    @PersistenceContext(unitName = "ProjectDistrubutedPU")
    private EntityManager entityManager;

    public List<Message> getMessages() {
        TypedQuery<Message> query = entityManager.createNamedQuery("Message.findAll", Message.class);
        List<Message> l = query.getResultList();
        Collections.reverse(l);
        l = l.subList(0, Math.min(l.size(), 24));
        return l;

    }

    @Interceptors(ProfanityInterceptor.class)
    public Message addNew(Message m) {
        String[] split = m.getText().split(" ");
        StringBuilder sb = new StringBuilder();
        List<UserAccount> ul = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            boolean tagged =false;
            if (split[i].startsWith("@")) {
                split[i] = split[i].substring(1, split[i].length());
                TypedQuery<UserAccount> query = entityManager.createNamedQuery("UserAccount.findByName", UserAccount.class);
                query.setParameter("username", split[i]);
                List<UserAccount> l = query.getResultList();
                if(!(l.isEmpty())){
                ul.add(l.get(0));
                sb.append("<b>");
                tagged= true;
                }
            }
            sb.append(split[i]);
            if(tagged){
                sb.append("</b>");
            }
            sb.append(" ");
        }
        m.setTaggedUsers(ul);
        m.setText(sb.toString());
        entityManager.persist(m);
        return m;
    }

}
