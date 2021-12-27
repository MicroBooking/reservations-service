package graphql;

import beans.ReservationsBean;
import classes.Reservation;
import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@GraphQLClass
@ApplicationScoped
public class ReservationsQueries {
    @Inject
    private ReservationsBean reservationsBean;

    @GraphQLQuery
    public PaginationWrapper<Reservation> allReservations(@GraphQLArgument(name = "pagination")Pagination pagination,
                                                          @GraphQLArgument(name="sort") Sort sort,
                                                          @GraphQLArgument(name="filter") Filter filter) {
        return GraphQLUtils.process(reservationsBean.getReservations(), pagination, sort, filter);
    }

    @GraphQLQuery
    public Reservation getReservation(@GraphQLArgument(name = "id") Integer id) {
        return reservationsBean.getReservationById(id);
    }
}
