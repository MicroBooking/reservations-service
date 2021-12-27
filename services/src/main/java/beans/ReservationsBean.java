package beans;

import classes.Reservation;
import converters.ReservationsConverter;
import entities.ReservationEntity;
import javax.ws.rs.NotFoundException;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class ReservationsBean {
    private Logger log = Logger.getLogger(ReservationsBean.class.getName());

    @Inject
    private EntityManager em;


    public List<Reservation> getReservations(){
        TypedQuery<ReservationEntity> query = em.createNamedQuery(
                "ReservationEntity.getAll", ReservationEntity.class);
        List<ReservationEntity> resultList = query.getResultList();

        return resultList.stream().map(ReservationsConverter::toDto).collect(Collectors.toList());
    }

    public Reservation getReservationById(Integer id) {
        ReservationEntity reservationEntity = em.find(ReservationEntity.class, id);

        if (reservationEntity == null) {
            throw new NotFoundException();
        }

        Reservation reservation = ReservationsConverter.toDto(reservationEntity);

        return reservation;
    }

    public Reservation createReservation(Reservation reservation) {
        ReservationEntity reservationEntity = ReservationsConverter.toEntity(reservation);

        try {
            beginTx();
            em.persist(reservationEntity);
            commitTx();
        }
        catch (Exception e){
            rollbackTx();
        }

        return ReservationsConverter.toDto(reservationEntity);
    }

    public boolean deleteReservation(Integer id) {
        ReservationEntity reservation = em.find(ReservationEntity.class, id);

        if (reservation != null) {
            try {
                beginTx();
                em.remove(reservation);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else {
            return false;
        }

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }




}
