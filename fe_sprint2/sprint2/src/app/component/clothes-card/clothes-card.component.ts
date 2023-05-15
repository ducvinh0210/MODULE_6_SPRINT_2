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
    this.username = '';
    this.showUsername();
  }


  showUsername() {
    this.username = this.tokenService.getUser().username;
    // console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isEmployee = this.roles.indexOf('ROLE_EMPLOYEE') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;

    if (this.username !== '') {
      this.clothesService.findCustomer(this.username).subscribe(customer => {

          console.log('day la customer' + customer);
          if (customer != null) {
            this.clothesService.findCartByUser(customer.id).subscribe(value => {
                console.log(' day la value' + value);
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


                      // this.clothesService.setQuantityProduct(customer.id).subscribe(next => {
                      //   console.log('abc');
                      // });
                      this.clothesService.setQuantityProduct(customer.id).subscribe(() => {

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


  descQuantity(id: number): void {
    this.clothesService.descQuantityCart(id).subscribe(() => {
      window.location.reload();
    }, error => {
      console.log(error);
    });

  }


  // ascQuantity(id: number): void {
  //   this.clothesService.ascQuantityCart(id).subscribe(() => {
  //     window.location.reload();
  //   }, error => {
  //     console.log(error);
  //   });
  // }


  ascQuantity(item: any): void {
    if (item.quantity >= item.quantityProduct) {

      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 1000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer);
          toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
      });

      Toast.fire({
        icon: 'warning',
        title: 'Số lượng sản phẩm trong kho không đủ!'
      }).then(r => location.replace('cart'));


    } else {
      this.clothesService.ascQuantityCart(item.id).subscribe(() => {
        window.location.reload();
      }, error => {
        console.log(error);
      });
    }


  }


  removeCart(id: number): void {
    Swal.fire({
      title: 'Bạn có chắc?',
      text: 'Xóa sản phẩm này khỏi giỏ hàng!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Có, tôi muốn xóa!',
      cancelButtonText: 'Đóng'
    }).then((result) => {
      if (result.isConfirmed) {
        this.clothesService.removeCart(id).subscribe(() => {
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
            title: 'Xóa khỏi giỏ hàng thành công!'
          });

          window.location.reload();
        }, error => {
          console.log(error);
        });
      }
    });
  }


  updateCart() {
    this.pay = 'block';
  }
}
