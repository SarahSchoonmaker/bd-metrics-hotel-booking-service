package com.amazon.ata.metrics.classroom.activity;

import com.amazon.ata.metrics.classroom.dao.ReservationDao;
import com.amazon.ata.metrics.classroom.dao.models.Reservation;
import com.amazon.ata.metrics.classroom.metrics.MetricsPublisher;
import com.amazonaws.services.cloudwatch.model.StandardUnit;

import javax.inject.Inject;

/**
 * Handles requests to cancel a reservation.
 */
public class CancelReservationActivity {

    private ReservationDao reservationDao;
    private MetricsPublisher metricsPublisher;

    /**
     * Constructs a CancelReservationActivity
     * @param reservationDao Dao used to update reservations.
     */
    @Inject
    public CancelReservationActivity(ReservationDao reservationDao, MetricsPublisher metricsPublisher) {
        this.reservationDao = reservationDao;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Cancels the given reservation.
     * @param reservationId of the reservation to cancel.
     * @return canceled reservation
     */
    public Reservation handleRequest(final String reservationId) {

        Reservation response = reservationDao.cancelReservation(reservationId);

        // Log the CanceledReservationCount metric
        metricsPublisher.addMetric("CanceledReservationCount", 1, StandardUnit.Count);

        return response;
    }
}
