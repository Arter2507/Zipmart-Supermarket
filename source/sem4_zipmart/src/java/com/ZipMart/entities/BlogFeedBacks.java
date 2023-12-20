/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ZipMart.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "BlogFeedBacks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlogFeedBacks.findAll", query = "SELECT b FROM BlogFeedBacks b"),
    @NamedQuery(name = "BlogFeedBacks.findByFeedbackID", query = "SELECT b FROM BlogFeedBacks b WHERE b.feedbackID = :feedbackID"),
    @NamedQuery(name = "BlogFeedBacks.findByTitle", query = "SELECT b FROM BlogFeedBacks b WHERE b.title = :title"),
    @NamedQuery(name = "BlogFeedBacks.findByContent", query = "SELECT b FROM BlogFeedBacks b WHERE b.content = :content"),
    @NamedQuery(name = "BlogFeedBacks.findByDate", query = "SELECT b FROM BlogFeedBacks b WHERE b.date = :date")})
public class BlogFeedBacks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedbackID")
    private Integer feedbackID;
    @Size(max = 50)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "customerID", referencedColumnName = "customerID")
    @ManyToOne(optional = false)
    private Customers customerID;

    public BlogFeedBacks() {
    }

    public BlogFeedBacks(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlogFeedBacks)) {
            return false;
        }
        BlogFeedBacks other = (BlogFeedBacks) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ZipMart.entities.BlogFeedBacks[ feedbackID=" + feedbackID + " ]";
    }
    
}
