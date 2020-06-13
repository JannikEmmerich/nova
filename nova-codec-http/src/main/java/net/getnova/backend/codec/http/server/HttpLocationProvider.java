package net.getnova.backend.codec.http.server;

/**
 * Provides a {@link HttpLocation} fo the Http Server.
 *
 * @param <L> the {@link HttpLocation} witch should be provided
 */
public interface HttpLocationProvider<L extends HttpLocation<?>> {

    /**
     * Returns a {@link HttpLocation} witch has to be every time a new instance.
     *
     * @return the {@link HttpLocation} instance
     */
    L getLocation();
}
