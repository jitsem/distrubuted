/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jitse
 */
@Entity
@Table(name = "MESSAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM Message m"),
    @NamedQuery(name = "Message.findById", query = "SELECT m FROM Message m WHERE m.id = :id"),
    @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text")})
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "TEXT")
    private String text;
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "DATETIME_FIELD")
    private Date dateTimeField;
    @Lob
    @Basic(fetch = FetchType.LAZY) // this gets ignored anyway, but it is recommended for blobs
    @Column(name = "Picture")
    protected byte[] imageFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID")
    private UserAccount owner;

    @ManyToMany
    @JoinTable(
            name = "TAGGEDUSERS",
            joinColumns = @JoinColumn(name = "M_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "U_ID", referencedColumnName = "USERID"))
    private List<UserAccount> taggedUsers;

    public List<UserAccount> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(List<UserAccount> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public Date getDateTimeField() {
        return dateTimeField;
    }

    public void setDateTimeField(Date dateTimeField) {
        this.dateTimeField = dateTimeField;
    }

    public Message() {
        this.dateTimeField = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getText();
    }
    public boolean hasPic(){
        return imageFile != null;
    }

}
