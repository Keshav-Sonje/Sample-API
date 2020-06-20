import com.google.inject.AbstractModule;
import manager.StudentManager;
import manager.StudentManagerImpl;

public class ProjServiceModule extends AbstractModule {
    @Override
    public void configure(){
        bind(StudentManager.class).to(StudentManagerImpl.class);
    }
}
