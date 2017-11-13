package com.ricardo.munidenunciasapp.service;

import com.ricardo.munidenunciasapp.models.Denuncia;
import com.ricardo.munidenunciasapp.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

}
