package com.sefford.kor.samples.core.business;

import java.util.Arrays;

/**
 * Options class will allow to unify the same API in Network Requests
 * and not clogging on extending the functionality
 *
 * @author Saul Diaz <sefford@gmail.com>
 */
public class Options {

    /**
     * Params list for different configurations
     */
    final String[] params;
    /**
     * Body for Posting requests
     */
    final Object body;
    /**
     * Default error message for responses
     */
    String defaultErrorMessage;

    /**
     * Creates a new instance of Options
     *
     * @param params Params list for different configurations
     * @param body   Body for Posting requests
     */
    Options(String[] params, Object body) {
        this.params = params != null ? Arrays.copyOf(params, params.length) : null;
        this.body = body;
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Returns the Params configuration
     *
     * @return Params list for different configurations
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Gets the Body of the Request
     *
     * @return Body for Posting requests
     */
    public Object getBody() {
        return body;
    }

    /**
     * Class
     */
    public static class Builder {
        /**
         * Params list for different configurations
         */
        String[] params;
        /**
         * Body for Posting requests
         */
        Object body;
        /**
         * Creates a new Builder Instance
         */
        Builder() {
            // Empty
        }

        /**
         * Sets the params for the Requests
         *
         * @param params Params list for different configurations
         * @return The builder instance
         */
        public Builder setParams(String... params) {
            this.params = Arrays.copyOf(params, params.length);
            return this;
        }

        /**
         * @param body Body for Posting requests
         * @return The builder instance
         */
        public Builder setBody(Object body) {
            this.body = body;
            return this;
        }

        /**
         * Creates a new Options instance
         *
         * @return New Options instance with the configured elements
         */
        public Options create() {
            return new Options(params, body);
        }
    }
}
