/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import Ejb.MessageEJB;
import Entities.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

/**
 *
 * @author jitse
 */
@ManagedBean
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
    private Part picture;

    public MessageEJB getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageEJB messageEJB) {
        this.messageEJB = messageEJB;
    }

    public Part getPicture() {
        return picture;
    }

    public void setPicture(Part picture) {
        this.picture = picture;
    }

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

    public String viewMessages() {
        return "index.xhtml";
    }

    public String addNewMessage() {
        Message message = new Message();
        message.setText(messageText);
        if (picture != null) {
            try {
                byte[] buffer = new byte[8192];
                int bytesRead;
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                InputStream input = picture.getInputStream();
                if (input.available() < 1000000) {
                    
                
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
                byte[] image = output.toByteArray();
                message.setImageFile(image);
                }else{
                    message.setText(message.getText().concat(" (PICTURE TO BIG)"));
                }
                
            } catch (Exception e) {
            }

        }

        message = messageEJB.addNew(message);
        messageList = messageEJB.getMessages();
        return "index.xhtml";
    }

}
