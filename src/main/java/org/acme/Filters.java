package org.acme;

import filters.ClientFilter;
import filters.FilterIn;
import filters.FilterOut;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestContext;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientRequestFilter;
import org.jboss.resteasy.reactive.client.spi.ResteasyReactiveClientResponseFilter;
@Provider
public class Filters implements ContainerRequestFilter, ContainerResponseFilter, ResteasyReactiveClientResponseFilter, ResteasyReactiveClientRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) {
        new FilterIn().filterIn(requestContext);
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        new FilterOut().filterOut(containerRequestContext,containerResponseContext);
    }

    @Override
    public void filter(ResteasyReactiveClientRequestContext requestContext) {
       new ClientFilter().filter(requestContext);
    }

    @Override
    public void filter(ResteasyReactiveClientRequestContext requestContext, ClientResponseContext responseContext) {
        new ClientFilter().filter(requestContext,responseContext);
    }
}
