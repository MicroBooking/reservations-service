package graphql;

import beans.ReservationsBean;
import classes.Reservation;
import classes.ReservationGraphQLDto;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import converters.ReservationsConverter;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;

@GraphQLClass
@ApplicationScoped
public class ReservationMutations {
    @Inject
    private ReservationsBean reservationsBean;

    @GraphQLMutation
    public Reservation addReservation(@GraphQLArgument(name = "reservation") ReservationGraphQLDto reservationGraphQLDto) {
        try {
            Reservation reservation = reservationsBean.createReservation(ReservationsConverter.fromGraphQLToDto(reservationGraphQLDto));
            return  reservation;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @GraphQLMutation
    public DeleteResponse deleteReservation(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(reservationsBean.deleteReservation(id));
    }
}
