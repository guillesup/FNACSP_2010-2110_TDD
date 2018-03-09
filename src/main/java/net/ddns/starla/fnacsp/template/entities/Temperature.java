package net.ddns.starla.fnacsp.template.entities;

/**
 * This class abstracts the Temperature entity
 */
public final class Temperature extends Entity {
    public static final double MIN_VALUE = -89.2;
    public static final double MAX_VALUE = 54.0;

    /**
     * @param degrees Between [-89.2, 54.0] °C
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Lowest_temperatures_ever_recorded">Lowest temperatures ever recorded</a>
     * @see <a href="https://en.wikipedia.org/wiki/List_of_weather_records#Highest_temperatures_ever_recorded">Highest temperatures ever recorded</a>
     */
    public Temperature(double degrees) {
        super(degrees);
    }

    /**
     * It should throw a runtime exception if it doesn't belong to the valid interval or range.
     */
    @Override
    protected void assesInput() {
        if (!isItValid())
            throw new TemperatureException(
                    "Temperature must be between [" + Temperature.MIN_VALUE + ", " + Temperature.MAX_VALUE + "] °C");
    }

    /**
     * @return true, if it belongs to the valid interval or range.
     */
    private boolean isItValid() {
        return ((double) value >= MIN_VALUE && (double) value <= MAX_VALUE);
    }
}

class TemperatureException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    TemperatureException(String message) {
        super(message);
    }
}