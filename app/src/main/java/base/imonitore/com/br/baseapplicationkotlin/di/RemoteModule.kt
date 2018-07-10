package base.imonitore.com.br.baseapplicationkotlin.di

import android.content.Context
import base.imonitore.com.br.baseapplication.servicesmanager.MockInterceptor
import base.imonitore.com.br.baseapplicationkotlin.loading.LoadingDialog
import base.imonitore.com.br.baseapplicationkotlin.remoteconfig.FRC
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.RemoteConstant
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.midia.MidiaRest
import base.imonitore.com.br.baseapplicationkotlin.repository.remote.user.UserRest
import base.imonitore.com.br.baseapplicationkotlin.utils.ConnectivityAgentUtils
import base.imonitore.com.br.baseapplicationkotlin.utils.JSONUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {

    @Provides @Singleton fun provideGson(): Gson =
            GsonBuilder().setLenient().create()

    @Provides @Singleton fun provideMockInterceptor(context: Context): MockInterceptor =
            MockInterceptor(context)

    @Provides @Singleton fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(if(ApplicationBase.isMockAmbiente) mockInterceptor
                        else HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build()

    @Provides @Singleton fun provideRetrofit(okHttpClient: OkHttpClient, frc: FRC): Retrofit =
            Retrofit.Builder()
                    .baseUrl(frc.getBaseUrlFlavor())
                    .addConverterFactory(GsonConverterFactory.create(JSONUtils.newGson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides @Singleton fun provideUserRest(retrofit: Retrofit): UserRest =
            retrofit.create(UserRest::class.java)

    @Provides @Singleton fun provideMidiaRest(retrofit: Retrofit): MidiaRest =
            retrofit.create(MidiaRest::class.java)

    @Provides @Singleton fun provideLoadingDialog(context: Context): LoadingDialog =
            LoadingDialog(context)

    @Provides @Singleton fun provideFRC(): FRC =
            FRC()

    @Provides @Singleton fun provideConnectivityAgentUtils(context: Context): ConnectivityAgentUtils =
            ConnectivityAgentUtils(context)
}