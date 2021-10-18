package models;

import com.google.inject.AbstractModule;
import models.repository.UsuarioRepository;
import models.repository.impl.ImplUsuarioRepository;

/**
 * Guice module - Pacote repository
 *
 * <p>Autor: GPortes</p>
 */
public class Module extends AbstractModule {

    @Override
    protected void configure() {

        bind( UsuarioRepository.class ).to( ImplUsuarioRepository.class );

    }

}