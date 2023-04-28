import {Component, OnInit} from '@angular/core';
import {ICart} from '../../model/i-cart';
import {ClothesService} from '../../service/clothes.service';
import {Router} from '@angular/router';
import {TokenStorageService} from '../../service/token-storage.service';
import {Title} from '@angular/platform-browser';
import Swal from 'sweetalert2';
import {render} from 'creditcardpayments/creditCardPayments';


@Component({
  selector: 'app-clothes-card',
  templateUrl: './clothes-card.component.html',
  styleUrls: ['./clothes-card.component.css']
})
export class ClothesCardComponent implements OnInit {

  cart: ICart[];
  totalPrice = 0;
  finalPrice = 0;
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  isEmployee = false;
  pay = 'none';


  constructor(private clothesService: ClothesService,
              private router: Router,
              private tokenService: TokenStorageService,
              private title: Title) {
    title.setTitle('Giỏ hàng');
  }
  ngOnInit(): void {
  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isEmployee = this.roles.indexOf('ROLE_EMPLOYEE') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;

    if (this.username !== '') {
      this.clothesService.findCustomer(this.username).subscribe(customer => {
          if (customer != null) {
            this.clothesService.findCartByUser(customer.id).subscribe(value => {
                this.cart = value;

                for (const item of value) {
                  this.totalPrice += item.price * item.quantity;
                  this.finalPrice += item.price * (1 - item.discount / 100) * item.quantity;
                }
                console.log(Math.round(this.finalPrice / 23000 * 100) / 100);
                render(
                  {
                    id: '#myPaypal',
                    value: '' + Math.round(this.finalPrice / 23000 * 100) / 100,
                    currency: 'USD',
                    onApprove: (details) => {
                      this.clothesService.paymentClothes(customer.id).subscribe(() => {

                        const Toast = Swal.mixin({
                          toast: true,
                          position: 'top-end',
                          showConfirmButton: false,
                          timer: 2000,
                          timerProgressBar: true,
                          didOpen: (toast) => {
                            toast.addEventListener('mouseenter', Swal.stopTimer);
                            toast.addEventListener('mouseleave', Swal.resumeTimer);
                          }
                        });

                        Toast.fire({
                          icon: 'success',
                          title: 'Thanh toán thành công!'
                        }).then(r => window.location.replace(''));
                      }, error => {
                        console.log(error);
                      });
                    }
                  }
                );
              },
              error => {
                console.log(error);
              });
          }
        },
        error => {
          console.log(error);
        });
    }
  }



}
