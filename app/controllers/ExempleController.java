package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.UsuarioService;

import javax.inject.Inject;

public class ExempleController extends Controller {

    private final UsuarioService usuarioService;

    @Inject
    public ExempleController(
            final UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }

    public Result apiGet(final Http.Request request, final String nome) {

        System.out.println(nome);

        return ok("API GET JAVA PLAY");
    }

    public Result apiPost(final Http.Request request) {

        System.out.println(request.body());

        return ok("API POST JAVA PLAY");
    }

    public Result apiPut(final Http.Request request, final Long id) {

        System.out.println(id);

        return ok("API PUT JAVA PLAY");
    }

    public Result apiDelete(final Http.Request request, final Long id) {

        System.out.println(id);

        return ok("API DELETE JAVA PLAY");
    }
}
