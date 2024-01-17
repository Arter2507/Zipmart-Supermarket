/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zipmart.ejb.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TUONG
 */
@Entity
@Table(name = "Cards")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cards.findAll", query = "SELECT c FROM Cards c"),
    @NamedQuery(name = "Cards.findById", query = "SELECT c FROM Cards c WHERE c.id = :id"),
    @NamedQuery(name = "Cards.findByCardName", query = "SELECT c FROM Cards c WHERE c.cardName = :cardName"),
    @NamedQuery(name = "Cards.findByCardNumber", query = "SELECT c FROM Cards c WHERE c.cardNumber = :cardNumber"),
    @NamedQuery(name = "Cards.findByValueFrom", query = "SELECT c FROM Cards c WHERE c.valueFrom = :valueFrom"),
    @NamedQuery(name = "Cards.findByExpirationDate", query = "SELECT c FROM Cards c WHERE c.expirationDate = :expirationDate"),
    @NamedQuery(name = "Cards.findByCvvNumber", query = "SELECT c FROM Cards c WHERE c.cvvNumber = :cvvNumber"),
    @NamedQuery(name = "Cards.findByCardType", query = "SELECT c FROM Cards c WHERE c.cardType = :cardType"),
    @NamedQuery(name = "Cards.findByPoint", query = "SELECT c FROM Cards c WHERE c.point = :point"),
    @NamedQuery(name = "Cards.findByRank", query = "SELECT c FROM Cards c WHERE c.rank = :rank"),
    @NamedQuery(name = "Cards.findByCreatedate", query = "SELECT c FROM Cards c WHERE c.createdate = :createdate"),
    @NamedQuery(name = "Cards.findByModifiedate", query = "SELECT c FROM Cards c WHERE c.modifiedate = :modifiedate"),
    @NamedQuery(name = "Cards.findByCreateby", query = "SELECT c FROM Cards c WHERE c.createby = :createby"),
    @NamedQuery(name = "Cards.findByModifieby", query = "SELECT c FROM Cards c WHERE c.modifieby = :modifieby")})
public class Cards implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "cardName")
    private String cardName;
    @Size(max = 16)
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "valueFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date valueFrom;
    @Column(name = "expirationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "cvvNumber")
    private Integer cvvNumber;
    @Size(max = 20)
    @Column(name = "cardType")
    private String cardType;
    @Column(name = "point")
    private BigInteger point;
    @Size(max = 50)
    @Column(name = "rank")
    private String rank;
    @Column(name = "createdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;
    @Column(name = "modifiedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedate;
    @Size(max = 255)
    @Column(name = "createby")
    private String createby;
    @Size(max = 255)
    @Column(name = "modifieby")
    private String modifieby;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cards")
    private Collection<CustomerCard> customerCardCollection;

    public Cards() {
    }

    public Cards(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Date valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(Integer cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public BigInteger getPoint() {
        return point;
    }

    public void setPoint(BigInteger point) {
        this.point = point;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getModifiedate() {
        return modifiedate;
    }

    public void setModifiedate(Date modifiedate) {
        this.modifiedate = modifiedate;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public String getModifieby() {
        return modifieby;
    }

    public void setModifieby(String modifieby) {
        this.modifieby = modifieby;
    }

    @XmlTransient
    public Collection<CustomerCard> getCustomerCardCollection() {
        return customerCardCollection;
    }

    public void setCustomerCardCollection(Collection<CustomerCard> customerCardCollection) {
        this.customerCardCollection = customerCardCollection;
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
        if (!(object instanceof Cards)) {
            return false;
        }
        Cards other = (Cards) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.zipmart.ejb.entities.Cards[ id=" + id + " ]";
    }
    
}
