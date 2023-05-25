import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './component/home/home.component';
import {LoginComponent} from './component/login/login.component';
import {ProductDetailComponent} from './component/product-detail/product-detail.component';
import {PaymentCardComponent} from './component/payment-card/payment-card.component';
import {UserCreateComponent} from './component/user-create/user-create.component';
import {ClothesListComponent} from './component/clothes-list/clothes-list.component';
import {ClothesCardComponent} from './component/clothes-card/clothes-card.component';
import {ClothesDetailComponent} from './component/clothes-detail/clothes-detail.component';
import {ClothesHistoryComponent} from './component/clothes-history/clothes-history.component';


const routes: Routes = [

  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'product-detail', component: ProductDetailComponent},
  {path: 'payment-cart', component: PaymentCardComponent},
  {path: 'user-create', component: UserCreateComponent},
  {path: 'product-list', component: ClothesListComponent},
  {path: 'cart', component: ClothesCardComponent},
  {path: 'detail/:id', component: ClothesDetailComponent},
  {path: 'history', component: ClothesHistoryComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
