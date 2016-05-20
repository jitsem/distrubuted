/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import Entities.UserAccount;
import java.util.ArrayList;
import javax.ejb.Singleton;

/**
 *
 * @author jitse
 */
@Singleton
public class CounterEJB {

    private int totalMessages=0;
    private ArrayList<UserAccount> users = new ArrayList<>();

   
    public int getCurrentHits(){
        return totalMessages++;
    }
    
     public int getTotalUsers() {
        return users.size();
    }
    
    public void addUser(UserAccount u){
        users.add(u);
    }
    
    public void removeUser(UserAccount u){
        users.remove(u);
    }
}
