package services;

import com.google.inject.AbstractModule;
import services.impl.UsuarioServiceImpl;

/**
 * Guice module - Pacote service
 *
 * <p>Autor: Mayke</p>
 */
public class Module extends AbstractModule {

    @Override
    protected void configure( ) {

        bind( UsuarioService.class ).to( UsuarioServiceImpl.class );

    }
}