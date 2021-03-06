package org.jpwh.model.simple;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Size(
    min = 5,
    max = 255,
    message = "Name is required, max 255 chars"
  )
  @Access(AccessType.PROPERTY)
  @Column(name = "name")
  private String name;

//  @Column(name = "START_PRICE", nullable = false)
  private BigDecimal initialPrice;

  private Date auctionEnd;

  @org.hibernate.annotations.Type(type = "yes_no")
  protected boolean verified;

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  @NotNull
  @Enumerated(EnumType.STRING)
  protected AuctionType auctionType = AuctionType.HIGHEST_DID;

  @Temporal(TemporalType.TIMESTAMP)
  @Future //аннотации на геттер или на public\protected поле
  public Date getAuctionEnd() {
    return auctionEnd;
  }

  public void setAuctionEnd(Date auctionEnd) {
    this.auctionEnd = auctionEnd;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getInitialPrice() {
    return initialPrice;
  }

  public void setInitialPrice(BigDecimal initialPrice) {
    this.initialPrice = initialPrice;
  }

  public AuctionType getAuctionType() {
    return auctionType;
  }

  public void setAuctionType(AuctionType auctionType) {
    this.auctionType = auctionType;
  }
}

enum AuctionType {
  HIGHEST_DID,
  LOWEST_BID,
  FIXED_PRICE
}
