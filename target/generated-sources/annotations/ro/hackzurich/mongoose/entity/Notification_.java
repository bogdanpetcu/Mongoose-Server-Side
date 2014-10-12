package ro.hackzurich.mongoose.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-10-12T01:47:37")
@StaticMetamodel(Notification.class)
public class Notification_ { 

    public static volatile SingularAttribute<Notification, String> toId;
    public static volatile SingularAttribute<Notification, Date> date;
    public static volatile SingularAttribute<Notification, Long> id;
    public static volatile SingularAttribute<Notification, Integer> type;
    public static volatile SingularAttribute<Notification, String> fromId;

}