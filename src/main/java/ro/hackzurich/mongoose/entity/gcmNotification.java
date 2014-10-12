/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.hackzurich.mongoose.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author bogdanpetcu
 */
public class gcmNotification {
   
    @XmlElement(name = "data")
    Notification notification;
    @XmlElement(name = "registration_ids")
    List<String> registration_id;

    public gcmNotification(Notification notification, String registration_id) {
        this.notification = notification;
        this.registration_id = new ArrayList<String>();
        this.registration_id.add(registration_id);
    }
    
    public gcmNotification(){
        
    }
    
    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public List<String> getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(List<String> registration_id) {
        this.registration_id = registration_id;
    }

}
