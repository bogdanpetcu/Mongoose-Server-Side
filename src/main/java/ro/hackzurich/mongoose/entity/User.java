/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ro.hackzurich.mongoose.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bogdanpetcu
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByFbId", query = "SELECT u FROM User u WHERE u.fbId = :fbId"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByInitiateCredit", query = "SELECT u FROM User u WHERE u.initiateCredit = :initiateCredit"),
    @NamedQuery(name = "User.findByGcmId", query = "SELECT u FROM User u WHERE u.gcmId = :gcmId"),
    @NamedQuery(name = "User.findByDonatedSum", query = "SELECT u FROM User u WHERE u.donatedSum = :donatedSum"),
    @NamedQuery(name = "User.findByReviewPoints", query = "SELECT u FROM User u WHERE u.reviewPoints = :reviewPoints")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "fb_id")
    private String fbId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "initiate_credit")
    private int initiateCredit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4096)
    @Column(name = "gcm_id")
    private String gcmId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "donated_sum")
    private int donatedSum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "review_points")
    private int reviewPoints;

    public User() {
    }

    public User(String fbId) {
        this.fbId = fbId;
    }

    public User(String fbId, String name, int initiateCredit, String gcmId, int donatedSum, int reviewPoints) {
        this.fbId = fbId;
        this.name = name;
        this.initiateCredit = initiateCredit;
        this.gcmId = gcmId;
        this.donatedSum = donatedSum;
        this.reviewPoints = reviewPoints;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitiateCredit() {
        return initiateCredit;
    }

    public void setInitiateCredit(int initiateCredit) {
        this.initiateCredit = initiateCredit;
    }

    public String getGcmId() {
        return gcmId;
    }

    public void setGcmId(String gcmId) {
        this.gcmId = gcmId;
    }

    public int getDonatedSum() {
        return donatedSum;
    }

    public void setDonatedSum(int donatedSum) {
        this.donatedSum = donatedSum;
    }

    public int getReviewPoints() {
        return reviewPoints;
    }

    public void setReviewPoints(int reviewPoints) {
        this.reviewPoints = reviewPoints;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fbId != null ? fbId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.fbId == null && other.fbId != null) || (this.fbId != null && !this.fbId.equals(other.fbId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.hackzurich.mongoose.entity.User[ fbId=" + fbId + " ]";
    }
    
}
