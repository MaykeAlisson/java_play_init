package system;

import org.slf4j.Logger;
import play.inject.ApplicationLifecycle;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Lifecycle da aplicação.
 *
 * <p>Autor: GPortes</p>
 */
@Singleton
public class ApplicationSetting {

    // Play:
    private final ApplicationLifecycle appLifecycle;
    private final Logger logger = getLogger("application");

    @Inject
    public ApplicationSetting(
            final ApplicationLifecycle appLifecycle
    ) {

        this.appLifecycle = appLifecycle;
        initialize();
    }

    private void initialize() {

        final LocalDateTime hoje = LocalDateTime.now();

        logger.info( "------------------------------------------------------------------------------------" );
        logger.info( "SITE - APLICACAO INICIADA EM {}",  hoje );
        logger.info( "------------------------------------------------------------------------------------" );

        this.appLifecycle.addStopHook( () -> {
            logger.info( "------------------------------------------------------------------------------------" );
            logger.info( "SITE - APLICACAO ENCERRADA EM: {}" ,  hoje );
            logger.info( "------------------------------------------------------------------------------------" );
            return completedFuture(null);
        });


    }

}
