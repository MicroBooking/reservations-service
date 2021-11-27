package converters;

import classes.Reservation;
import entities.ReservationEntity;

public class ReservationsConverter {
    public static Reservation toDto(ReservationEntity entity) {
        Reservation dto = new Reservation();

        dto.setReservationId(entity.getId());

        dto.setOwnerId(entity.getOwnerId());
        dto.setReserverId(entity.getReserverId());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setListingId(entity.getListingId());

        return dto;
    }

    public static ReservationEntity toEntity(Reservation dto) {
        ReservationEntity entity = new ReservationEntity();
        entity.setOwnerId(dto.getOwnerId());
        entity.setReserverId(dto.getReserverId());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setListingId(dto.getListingId());

        return entity;
    }
}
