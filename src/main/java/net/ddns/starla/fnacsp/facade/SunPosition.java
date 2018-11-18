package net.ddns.starla.fnacsp.facade;

import net.ddns.starla.fnacsp.template.algorithms.Algorithm;
import net.ddns.starla.fnacsp.template.algorithms.factory.AlgorithmFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Facade Design Pattern
 */
@SuppressWarnings("unused")
public final class SunPosition {

    private final Algorithm algorithm;

    /**
     * Computes the instant sun position using the highest precision algorithm (Algorithm #5) in combination with the
     * current date-time from the system clock in a specific timezone (zoneId).
     *
     * @param zoneId      zoneId not null (time-zone)
     * @param longitude   [0, 2PI] rad
     * @param latitude    [-PI/2,PI/2] rad
     * @param pressure    [0.85862324204293, 1.0696274364668] atm
     * @param temperature Between [-89.2, 54.0] °C
     * @see Algorithm#compute()
     */
    public SunPosition(String zoneId, double longitude, double latitude, double pressure, double temperature) {
        this("AlgorithmFive", ZonedDateTime.now().withZoneSameInstant(ZoneId.of(zoneId)), longitude,
                latitude, pressure, temperature);
    }

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param zonedDateTime      From 2010 to 2110 at UTC
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2,PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @see Algorithm#compute()
     */
    public SunPosition(String algorithmClassName, ZonedDateTime zonedDateTime, double longitude,
                       double latitude, double pressure, double temperature) {
        this.algorithm = AlgorithmFactory.createInstance(algorithmClassName, zonedDateTime, longitude, latitude,
                pressure, temperature);
    }

    /**
     * Computes the sun position
     */
    public void compute() {
        algorithm.compute();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    public double getZenith() {
        return algorithm.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    public double getAzimuth() {
        return algorithm.getAzimuth();
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    public double getRightAscension() {
        return algorithm.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    public double getDeclination() {
        return algorithm.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    public double getHourAngle() {
        return algorithm.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    public String getTimeOfDay() {
        return algorithm.timeOfDay();
    }

    /**
     * @return Elevation angle [0,PIM]
     */
    public double getElevation() {
        return algorithm.getElevation();
    }

    /**
     * @return zonedDateTime
     */
    public ZonedDateTime getZonedDateTime() {
        return algorithm.getZonedDateTime();
    }

    /**
     * @return longitude
     */
    public double getLongitude() {
        return algorithm.getLongitude();
    }

    /**
     * @return latitude
     */
    public double getLatitude() {
        return algorithm.getLatitude();
    }

    /**
     * @return pressure
     */
    public double getPressure() {
        return algorithm.getPressure();
    }

    /**
     * @return temperature
     */
    public double getTemperature() {
        return algorithm.getTemperature();
    }

    @Override
    public String toString() {
        return ""
                + "DateTime\t" + this.getZonedDateTime()
                + "\nZenith\t\t" + round(this.getZenith()) + " rad"
                + "\nAzimuth\t\t" + round(this.getAzimuth()) + " rad"
                + "\nRight Asc.\t" + round(this.getRightAscension()) + " rad"
                + "\nDeclination\t" + round(this.getDeclination()) + " rad"
                + "\nHour Angle\t" + round(this.getHourAngle()) + " rad"
                + "\nToD\t\t\t" + (this.getTimeOfDay());
    }

    private double round(double number) {
        return Math.round(number * 100.0) / 100.0;
    }
}
