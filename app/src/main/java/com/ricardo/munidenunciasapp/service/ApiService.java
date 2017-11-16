package com.ricardo.munidenunciasapp.service;

import com.ricardo.munidenunciasapp.models.Denuncia;
import com.ricardo.munidenunciasapp.models.Usuario;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    String API_BASE_URL = "https://munidenuncias-api-krobawsky.c9users.io";

    @GET("api/v1/usuarios")
    Call<List<Usuario>> getUsuarios();

    @FormUrlEncoded
    @POST("/api/v1/usuarios")
    Call<ResponseMessage> createUsuario(@Field("nombres") String nombre,
                                        @Field("correo") String correo,
                                        @Field("username") String username,
                                        @Field("password") String password);

    @GET("api/v1/denuncias")
    Call<List<Denuncia>> getDenuncias();

    @FormUrlEncoded
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> createDenuncia(@Field("usuarios_id") String usuarios_id,
                                        @Field("titulo") String titulo,
                                        @Field("nombre_usuario") String nombre_usuario,
                                        @Field("descripcion") String descripcion,
                                        @Field("latitud") String latitud,
                                        @Field("longitud") String longitud,
                                         @Field("ubicacion") String address);

    @Multipart
    @POST("/api/v1/denuncias")
    Call<ResponseMessage> createDenunciaWithImage(
            @Part("usuarios_id") RequestBody usuarios_id,
            @Part("titulo") RequestBody titulo,
            @Part("nombre_usuario") RequestBody nombre_usuario,
            @Part("descripcion") RequestBody descripcion,
            @Part("latitud") RequestBody latitud,
            @Part("longitud") RequestBody longitud,
            @Part("ubicacion") RequestBody address,
            @Part MultipartBody.Part imagen
    );


}
