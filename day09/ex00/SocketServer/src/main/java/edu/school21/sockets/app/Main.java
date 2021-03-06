package edu.school21.sockets.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.models.User;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);

        Args as = new Args(args);
        Server server = context.getBean(Server.class);
        server.setPort(as.port);
        server.start();
    }

    @Parameters(separators = "=")
    public static class Args {
        @Parameter(names = {"--port", "-p"})
        private Integer port;

        Args(String[] args) {
            JCommander.newBuilder()
                    .addObject(this)
                    .build()
                    .parse(args);
        }
    }
}