
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import resources.UserResource;

@Slf4j
public class ProjServiceApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new ProjServiceApplication().run(args);
        System.out.println("Hello World");
        System.out.println("commit or reset changes");
    }
    @Override
    public void initialize(Bootstrap<Configuration> b){

    }

    @Override
    public void run(Configuration c, Environment e){
        Injector injector = Guice.createInjector( new ProjServiceModule());
        UserResource user = injector.getInstance(UserResource.class);
        e.jersey().register(user);
    }

}
