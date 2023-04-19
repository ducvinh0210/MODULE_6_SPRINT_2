import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {ProductDetailComponent} from './component/product-detail/product-detail.component';
import {PaymentCardComponent} from './component/payment-card/payment-card.component';
import {UserCreateComponent} from './component/user-create/user-create.component';


const routes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'product-detail', component: ProductDetailComponent},
  {path: 'payment-cart', component: PaymentCardComponent},
  {path: 'user-create', component: UserCreateComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
