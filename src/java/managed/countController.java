/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import Ejb.MessageCounter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;

/**
 *
 * @author jitse
 */
@ManagedBean
@Named(value = "countController")
@SessionScoped
public class countController implements Serializable {

     @EJB
    private MessageCounter counter;
    private int messageCount;

    public int getMessageCount() {
        messageCount = counter.getCurrentHits();
        return messageCount;
    }

    public void setMessageCount(int newCount) {
        this.messageCount = newCount;
    }
    
    public countController() {
    }
    
    
}
