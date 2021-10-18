package system;

import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import views.html.erros.pagina_erro_client;
import views.html.erros.pagina_erro_server;
import views.html.erros.pagina_nao_localizada;

import javax.inject.Singleton;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

import static java.lang.String.format;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.NOT_FOUND;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;
import static play.mvc.Results.status;
/**
 * https://www.playframework.com/documentation/2.6.x/JavaErrorHandling
 */
@Singleton
public class ErrorHandler implements HttpErrorHandler {

    public CompletionStage<Result> onClientError(
            final RequestHeader request,
            int statusCode,
            final String message
    ) {

        final String url = getUrl( request );
        final boolean isChamadaApi = isChamadaApi( url );

        if( statusCode == NOT_FOUND )
            return isChamadaApi
                    ? completedFuture( notFound( format( "RECURSO [ %s ] NAO DISPONIVEL", url )  ) )
                    : completedFuture( notFound( pagina_nao_localizada.render() ) );

        if( statusCode == BAD_REQUEST )
            return isChamadaApi
                    ? completedFuture( badRequest(  message ) )
                    : completedFuture( badRequest( pagina_erro_client.render(statusCode,url,message) ) );

        return isChamadaApi
                ? completedFuture( status( statusCode, message ) )
                : completedFuture( status( statusCode, pagina_erro_client.render(statusCode,url,message) ) );
    }

    public CompletionStage<Result> onServerError(
            final RequestHeader request,
            final Throwable exception
    ) {

        final String url = getUrl( request );
        final boolean isChamadaApi = isChamadaApi( url );

        final String erroMsg =
                ( ( isChamadaApi && exception instanceof NullPointerException )
                        && (Objects.equals(exception.getMessage(), "Null stream")) )
                        ? format( "Recurso [ %s ] não localizado", url )
                        :  exception.toString() ;

        return isChamadaApi ?
                completedFuture( internalServerError( erroMsg ) ) :
                completedFuture( internalServerError( pagina_erro_server.render(url,erroMsg) ) );
    }

    private String getUrl( final RequestHeader requestHeader ) {

        return requestHeader != null ? requestHeader.uri() : "";
    }

    private boolean isChamadaApi( final String url ) {

        return url != null && url.contains( "/api/" );
    }
}