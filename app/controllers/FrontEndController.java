package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class FrontEndController extends Controller {

    /**
     * Tratamento para CORS.
     *
     * <p>Autor: GPortes</p>
     *
     * @param path
     *
     * @return
     */
    public Result options(String path ) {

        return ok()
                .withHeader("Access-Control-Allow-Origin", "*" )
                .withHeader("Allow", "*" )
                .withHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS" )
                .withHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin, X-Requested-With, Content-Type, Accept, Authorization" );
    }

}
