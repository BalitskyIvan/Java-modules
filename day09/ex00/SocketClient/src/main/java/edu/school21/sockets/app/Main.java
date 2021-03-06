package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class Main {

    public static void main(String[] args) {
        Args as = new Args(args);

        Client client = new Client();
        client.setPort(as.port);
        client.start();
    }

    @Parameters(separators = "=")
    public static class Args {
        @Parameter(names = {"--server-port", "-sp"})
        private Integer port;

        Args(String[] args) {
            JCommander.newBuilder()
                    .addObject(this)
                    .build()
                    .parse(args);
        }
    }
}
