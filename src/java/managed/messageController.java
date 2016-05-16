/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import Ejb.MessageEJB;
import Entities.Message;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author jitse
 */
@Named(value = "messageController")
@RequestScoped
public class messageController {


    /**
     * Creates a new instance of messageController
     */
    public messageController() {
    }
    
    @EJB
    private MessageEJB messageEJB;
    private String messageText;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    
    private List<Message> messageList = new ArrayList<>();
 
   public List<Message> getMessageList() {
        messageList = messageEJB.getMessages();
        return messageList;
    }
 
   
   public String viewMessages(){
        return "index.xhtml";
    }
   
    public String addNewMessage() {
        Message message = new Message();
        message.setText(messageText);
        message = messageEJB.addNew(message);
        messageList = messageEJB.getMessages();
        return "index.xhtml";
    }
}
