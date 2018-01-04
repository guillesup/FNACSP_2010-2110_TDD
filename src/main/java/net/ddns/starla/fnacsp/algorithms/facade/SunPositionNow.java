package net.ddns.starla.fnacsp.algorithms.facade;

import net.ddns.starla.fnacsp.algorithms.strategy.SunPosition;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Computes the instant sun position with the time-zone ID passed as String and with the Accuracy desired.
 */
public final class SunPositionNow {
    private final String algorithmClassName;
    private final String zoneId;
    private final double longitude;
    private final double latitude;
    private final double pressure;
    private final double temperature;
    private SunPosition sunPosition;

    /**
     * @param algorithmClassName Valid names are any Algorithm subclass
     * @param zoneId             time-zone ID
     * @param longitude          [0, 2PI] rad
     * @param latitude           [-PI/2, PI/2] rad
     * @param pressure           [0.85862324204293, 1.0696274364668] atm
     * @param temperature        Between [-89.2, 54.0] °C
     * @see SunPosition#of(String, ZonedDateTime, double, double, double, double)
     */
    public SunPositionNow(String algorithmClassName, String zoneId, double longitude, double latitude, double pressure, double temperature) {
        this.algorithmClassName = algorithmClassName;
        this.zoneId = zoneId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pressure = pressure;
        this.temperature = temperature;
    }

    public void computePosition() {
        sunPosition = SunPosition.of(algorithmClassName, ZonedDateTime.now(ZoneId.of(zoneId)),
                longitude, latitude, pressure, temperature);

        sunPosition.computePosition();
    }

    /**
     * @return Zenith angle [0,PI] rad
     */
    public double getZenith() {
        return sunPosition.getZenith();
    }

    /**
     * @return Azimuth angle [-PI,PI] rad
     */
    public double getAzimuth() {
        return sunPosition.getAzimuth();
    }

    /**
     * @return Right ascension [0,2PI] rad
     */
    public double getRightAscension() {
        return sunPosition.getRightAscension();
    }

    /**
     * @return Declination [-PI/2, PI/2] rad
     */
    public double getDeclination() {
        return sunPosition.getDeclination();
    }

    /**
     * @return Hour angle [-PI,PI] rad
     */
    public double getHourAngle() {
        return sunPosition.getHourAngle();
    }

    /**
     * @return True if the sun is above the horizon
     */
    public boolean isItDaylight() {
        return sunPosition.isItDaylight();
    }

    /**
     * @return Elevation angle [0,PIM]
     */
    public double getElevation() {
        return sunPosition.getElevation();
    }

    /**
     * @return ZonedDateTime used to compute the sun's position
     */
    public ZonedDateTime getZonedDateTime() {
        return sunPosition.getZonedDateTime();
    }

}