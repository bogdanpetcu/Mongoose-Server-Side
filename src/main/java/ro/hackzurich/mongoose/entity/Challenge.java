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
@Table(name = "Challenge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Challenge.findAll", query = "SELECT c FROM Challenge c"),
    @NamedQuery(name = "Challenge.findById", query = "SELECT c FROM Challenge c WHERE c.id = :id"),
    @NamedQuery(name = "Challenge.findByFromId", query = "SELECT c FROM Challenge c WHERE c.fromId = :fromId"),
    @NamedQuery(name = "Challenge.findByToId", query = "SELECT c FROM Challenge c WHERE c.toId = :toId"),
    @NamedQuery(name = "Challenge.findByCauseId", query = "SELECT c FROM Challenge c WHERE c.causeId = :causeId"),
    @NamedQuery(name = "Challenge.findByImageUrl", query = "SELECT c FROM Challenge c WHERE c.imageUrl = :imageUrl"),
    @NamedQuery(name = "Challenge.findByVideoUrl", query = "SELECT c FROM Challenge c WHERE c.videoUrl = :videoUrl"),
    @NamedQuery(name = "Challenge.findByStatus", query = "SELECT c FROM Challenge c WHERE c.status = :status"),
    @NamedQuery(name = "Challenge.findByToIdAndByStatus", query = "SELECT c FROM Challenge c WHERE c.toId = :toId AND c.status = :status"),
    @NamedQuery(name = "Challenge.getNextID", query = "SELECT MAX (c.id)+1 FROM Challenge c")})
public class Challenge implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "from_id")
    private String fromId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "to_id")
    private String toId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cause_id")
    private long causeId;
    @Size(max = 256)
    @Column(name = "image_url")
    private String imageUrl;
    @Size(max = 256)
    @Column(name = "video_url")
    private String videoUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public Challenge() {
    }

    public Challenge(Long id) {
        this.id = id;
    }

    public Challenge(Long id, String fromId, String toId, long causeId, int status) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.causeId = causeId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getCauseId() {
        return causeId;
    }

    public void setCauseId(long causeId) {
        this.causeId = causeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Challenge)) {
            return false;
        }
        Challenge other = (Challenge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ro.hackzurich.mongoose.entity.Challenge[ id=" + id + " ]";
    }
    
}
