package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Greeting hello() {
        return new Greeting("Hello, Jib!");
    }

    public static class Greeting {
        private String text;

        public Greeting(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}