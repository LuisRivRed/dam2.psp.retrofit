package com.psp.data

import com.google.gson.GsonBuilder
import com.psp.model.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import java.security.KeyStore
import javax.net.ssl.TrustManagerFactory
import java.security.cert.CertificateFactory
import java.io.InputStream

object ApiClient {

    private const val BASE_URL = "https://172.20.10.4:8080/"  // Cambiar a HTTPS

    private val gson = GsonBuilder().create()

    private val authInterceptor = AuthInterceptor()

    // configuramos el SSL
    private val okHttpClient: OkHttpClient
        get() {
            val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, null)

            // Carga el certificado
            val certificateFactory = CertificateFactory.getInstance("X.509")
            val certInputStream: InputStream = ApiClient::class.java.classLoader.getResourceAsStream("res/raw/your_certificate.crt") // Ruta al certificado
            val cert = certificateFactory.generateCertificate(certInputStream)

            keyStore.setCertificateEntry("serverCert", cert)

            trustManagerFactory.init(keyStore)

            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustManagerFactory.trustManagers, null)

            return OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, trustManagerFactory.trustManagers[0] as javax.net.ssl.X509TrustManager)
                .addInterceptor(authInterceptor)
                .build()
        }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService: AlumnoService = retrofit.create(AlumnoService::class.java)

    fun setToken(token: String) {
        authInterceptor.setToken(token)
    }
}