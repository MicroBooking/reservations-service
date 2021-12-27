package graphql;

import beans.ReservationsBean;
import classes.Reservation;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class ReservationMutations {
    @Inject
    private ReservationsBean reservationsBean;

    @GraphQLMutation
    public Reservation addReservation(@GraphQLArgument(name = "reservation") Reservation reservation) {
        reservationsBean.createReservation(reservation);
        return reservation;
    }

    @GraphQLMutation
    public DeleteResponse deleteReservation(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(reservationsBean.deleteReservation(id));
    }
}
