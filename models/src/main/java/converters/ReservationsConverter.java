package converters;

import classes.Reservation;
import classes.ReservationGraphQLDto;
import entities.ReservationEntity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static Reservation fromGraphQLToDto(ReservationGraphQLDto reservationGraphQLDto) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(reservationGraphQLDto.getStartDate());
        Date endDate = formatter.parse(reservationGraphQLDto.getEndDate());


        Reservation reservation = new Reservation();
        reservation.setOwnerId(reservationGraphQLDto.getOwnerId());
        reservation.setReserverId(reservationGraphQLDto.getReserverId());
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setListingId(reservationGraphQLDto.getListingId());
        return reservation;
    }
}
