package entities;

import jdk.jfr.Name;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="reservation")
@NamedQueries(value= {
        @NamedQuery(name="ReservationEntity.getAll",
        query = "SELECT reservation FROM ReservationEntity reservation")
})
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="ownerId")
    private Integer ownerId;

    @Column(name="reserverId")
    private Integer reserverId;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @Column(name="listing_id")
    private Integer listingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getReserverId() {
        return reserverId;
    }

    public void setReserverId(Integer reserverId) {
        this.reserverId = reserverId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }
}
