/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import Ejb.CounterEJB;
import Entities.UserAccount;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author jitse
 */
@ManagedBean
@Named(value = "StatusController")
@RequestScoped
public class StatusController implements Serializable {

     @EJB
    private CounterEJB counter;
    private int messageCount;
    private int upTime;
    private ArrayList<UserAccount> onlineUsers;
    
    public int getUpTime() {
        return counter.getMinutesOnline();
    }

    public void setUpTime(int upTime) {
        this.upTime = upTime;
    }

    public ArrayList<UserAccount> getOnlineUsers() {
        return counter.getUsers();
    }

    public void setOnlineUsers(ArrayList<UserAccount> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }
    

    public int getMessageCount() {
        messageCount = counter.getCurrentMessages();
        return messageCount;
    }

    public void setMessageCount(int newCount) {
        this.messageCount = newCount;
    }
    
    public StatusController() {
    }
    
    
}
