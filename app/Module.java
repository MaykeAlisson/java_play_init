import com.google.inject.AbstractModule;
import system.ApplicationSetting;

public class Module extends AbstractModule {

    @Override
    public void configure() {

        //
        // Ciclo de vida da aplicação:
        //
        bind( ApplicationSetting.class ).asEagerSingleton();

    }

}
