package com.mat_brandao.saudeapp.network.retrofit;

import com.mat_brandao.saudeapp.domain.model.CreateGroup;
import com.mat_brandao.saudeapp.domain.model.Establishment;
import com.mat_brandao.saudeapp.domain.model.Grupo;
import com.mat_brandao.saudeapp.domain.model.Installation;
import com.mat_brandao.saudeapp.domain.model.MembroGrupo;
import com.mat_brandao.saudeapp.domain.model.Post;
import com.mat_brandao.saudeapp.domain.model.PostContent;
import com.mat_brandao.saudeapp.domain.model.PostResponse;
import com.mat_brandao.saudeapp.domain.model.Rating;
import com.mat_brandao.saudeapp.domain.model.Remedy;
import com.mat_brandao.saudeapp.domain.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by infosolo2 on 6/24/2015.
 */
public interface RetrofitInterface {
    @POST("appCivicoRS/rest/instalacoes")
    Observable<Response<Installation>> createInstallation(@Body Installation installation);

    @PUT("appCivicoRS/rest/instalacoes")
    Observable<Response<Installation>> updateInstallation(@Body Installation installation);

    @GET("appCivicoRS/rest/instalacoes/{installationId}")
    Observable<Response<Installation>> getInstallation(@Path("installationId") Long installationId);

    @GET("appCivicoRS/rest/pessoas/autenticar")
    Observable<Response<User>> login(@Header("email") String email, @Header("senha") String password);

    @POST("appCivicoRS/rest/pessoas")
    Observable<Response<ResponseBody>> createUser(@Body User user);

    @GET("appCivicoRS/rest/pessoas/{codUsuario}")
    Observable<Response<User>> getUser(@Path("codUsuario") Long userId);

    @FormUrlEncoded
    @POST("appCivicoRS/rest/pessoas/redefinirSenha")
    Observable<Response<ResponseBody>> redefinePassword(@Field("email") String email);

    @GET("appCivicoRS/rest/pessoas/autenticar")
    Observable<Response<User>> loginWithFacebook(@Header("email") String email, @Header("facebookToken") String token);

    @GET("appCivicoRS/rest/pessoas/autenticar")
    Observable<Response<User>> loginWithGoogle(@Header("email") String email, @Header("googleToken") String token);

    @Multipart
    @POST("appCivicoRS/rest/pessoas/{userId}/fotoPerfil")
    Observable<Response<ResponseBody>> saveProfilePhoto(@Path("userId") long userId,
                                                        @Part MultipartBody.Part filePart);

    @GET("appCivicoRS/rest/pessoas/{userId}/fotoPerfil")
    Observable<Response<ResponseBody>> getProfilePhoto(@Path("userId") long userId);

    @GET("appCivicoRS/rest/postagens/tipopostagem/{codTipoPostagem}/tipoobjeto/{codTipoObjetoDestino}/objeto/{codObjetoDestino}")
    Observable<Response<Rating>> getObjectRating(@Path("codTipoPostagem") Long codTipoPostagem, @Path("codTipoObjetoDestino") Long codTipoObjetoDestino,
                                                 @Path("codObjetoDestino") Long codObjetoDestino);

    @GET("appCivicoRS/rest/postagens")
    Observable<Response<List<PostResponse>>> getPosts(@Query("codAplicativo") Long codAplicativo,
                                                      @Query("codAutor") Long codAutor, @Query("codTipoObjetoDestino") Long codTipoObjetoDestino,
                                                      @Query("codTiposPostagem") Long codTipoPostagem, @Query("codObjetoDestino") Long codObjetoDestino);

    @GET("appCivicoRS/rest/postagens/{codPostagem}/conteudos/{codConteudo}")
    Observable<Response<PostContent>> getPostContent(@Path("codPostagem") Long codPostagem, @Path("codConteudo") Long codConteudo);

    @POST("appCivicoRS/rest/postagens")
    Observable<Response<ResponseBody>> createPost(@Body Post post);

    @DELETE("appCivicoRS/rest/postagens/{codPostagem}/conteudos/{codConteudo}")
    Observable<Response<ResponseBody>> deleteContent(@Path("codPostagem") Long codPostagem, @Path("codConteudo") Long codConteudo);

    @POST("appCivicoRS/rest/postagens/{codPostagem}/conteudos")
    Observable<Response<ResponseBody>> createContent(@Path("codPostagem") Long codPostagem, @Body PostContent content);

    @PUT("appCivicoRS/rest/postagens/{codPostagem}/conteudos/{codConteudo}")
    Observable<Response<ResponseBody>> updateContent(@Path("codPostagem") Long codPostagem, @Path("codConteudo") Long codConteudo,
                                                     @Body PostContent content);

    @GET("mapa-da-saude/rest/estabelecimentos/latitude/{latitude}/longitude/{longitude}/raio/{raio}")
    Observable<Response<List<Establishment>>> getEstablishmentsByGeoLocation(
            @Path("latitude") Double latitude, @Path("longitude") Double longitude,
            @Path("raio") Double radius, @Query("pagina") Integer pagination);

    @GET("mapa-da-saude/rest/estabelecimentos/latitude/{latitude}/longitude/{longitude}/raio/{raio}")
    Observable<Response<List<Establishment>>> getEmergencyEstablishmentsByGeoLocation(
            @Path("latitude") Double latitude, @Path("longitude") Double longitude,
            @Path("raio") Double radius, @Query("pagina") Integer pagination, @Query("categoria") String category);

    @GET("mapa-da-saude/rest/estabelecimentos/unidade/{codUnidade}")
    Observable<Response<List<Establishment>>> getEstablishmentByCod(@Path("codUnidade") Long establishmentCod);

    @GET("mapa-da-saude/rest/estabelecimentos/")
    Observable<Response<List<Establishment>>> getEstablishmentByName(@Query("nomeFantasia") String nomeFantasia, @Query("uf") String uf);
    @GET("mapa-da-saude/rest/estabelecimentos/")
    Observable<Response<List<Establishment>>> getUrgencyEstablishmentByName(@Query("nomeFantasia") String nomeFantasia, @Query("uf") String uf,
                                                                     @Query("categoria") String categoria);

    @GET("mapa-da-saude/rest/remedios")
    Observable<Response<List<Remedy>>> getRemedyByCod(@Query("codBarraEan") Long codBarraEan);

    @GET("mapa-da-saude/rest/remedios")
    Observable<Response<List<Remedy>>> getRemedies(@Query("codBarraEan") String barCode, @Query("produto") String name);

    @PUT("appCivicoRS/rest/pessoas/{codPessoa}")
    Observable<Response<ResponseBody>> updateUser(@Path("codPessoa") Long userId, @Body User user);

    @DELETE("/appCivicoRS/rest/pessoas/{codPessoa}")
    Observable<Response<ResponseBody>> removeAccount(@Path("codPessoa") long userId);

    @PUT("/appCivicoRS/rest/pessoas/reativar")
    Observable<Response<ResponseBody>> reactivateNormalAccount(@Header("email") String email, @Header("senha") String password);

    @PUT("/appCivicoRS/rest/pessoas/reativar")
    Observable<Response<ResponseBody>> reactivateFacebookAccount(@Header("facebookToken") String facebookToken);

    @PUT("/appCivicoRS/rest/pessoas/reativar")
    Observable<Response<ResponseBody>> reactivateGoogleAccount(@Header("googleToken") String googleToken);

    @GET("/appCivicoRS/rest/grupos")
    Observable<Response<List<Grupo>>> getGroups(@Query("codAplicativo") String codAplicativo,
                                                @Query("descricao") String descricao, @Query("codPessoa") Long userId);

    @POST("/appCivicoRS/rest/grupos")
    Observable<Response<ResponseBody>> createGroup(@Body CreateGroup createGroup);

    @GET("/appCivicoRS/rest/grupos/{codGrupo}/membros")
    Observable<Response<List<MembroGrupo>>> getGroupMembers(@Path("codGrupo") Integer groupId);

    @POST("/appCivicoRS/rest/grupos/{codGrupo}/membros")
    Observable<Response<ResponseBody>> joinGroup(@Path("codGrupo") Integer groupId, @Query("codUsuario") Long userId);

    @DELETE("/appCivicoRS/rest/grupos/{codGrupo}/membros/{codMembro}")
    Observable<Response<ResponseBody>> leaveGroup(@Path("codGrupo") Integer groupId, @Path("codMembro") Long memberId);


}
