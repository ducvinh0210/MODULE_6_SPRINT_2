import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { HomeComponent } from './component/home/home.component';
import { LoginComponent } from './component/login/login.component';
import { ProductDetailComponent } from './component/product-detail/product-detail.component';
import { PaymentCardComponent } from './component/payment-card/payment-card.component';
import { UserCreateComponent } from './component/user-create/user-create.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule} from 'angularx-social-login';
import { ClothesListComponent } from './component/clothes-list/clothes-list.component';
import { ClothesCardComponent } from './component/clothes-card/clothes-card.component';
import { ClothesDetailComponent } from './component/clothes-detail/clothes-detail.component';
import { ClothesHistoryComponent } from './component/clothes-history/clothes-history.component';
import {AuthInterceptor} from './component/security/auth.interceptor';



const googleLoginOptions = {
  scope: 'profile email',
  plugin_name: 'login'
};

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    ProductDetailComponent,
    PaymentCardComponent,
    UserCreateComponent,
    ClothesListComponent,
    ClothesCardComponent,
    ClothesDetailComponent,
    ClothesHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    SocialLoginModule,
    ReactiveFormsModule,

  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '612774287153-uthnsrl25on17doe8413il68ebv9c969.apps.googleusercontent.com',
              googleLoginOptions
            )
          },
        ]
      } as SocialAuthServiceConfig,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
