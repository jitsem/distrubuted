/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejb;

import javax.ejb.Singleton;

/**
 *
 * @author jitse
 */
@Singleton
public class MessageCounter {

    private int totalMessages=0;
    
    public int getCurrentHits(){
        return totalMessages++;
    }
}
