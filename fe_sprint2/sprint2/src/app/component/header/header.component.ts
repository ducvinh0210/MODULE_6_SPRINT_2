import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  quantityCart = 0;
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;


  constructor( private tokenService: TokenStorageService,
               private router: Router) { }

  ngOnInit(): void {
    this.username = '';
    this.showUsername();
  }

  showUsername() {
    this.username = this.tokenService.getUser().username;
    console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;

  }

  whenLogout() {
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
      icon: 'warning',
      title: 'Đăng xuất thành công!'
    });

    this.tokenService.logOut();
    this.router.navigateByUrl('');
    this.username = '';
    this.isCustomer = false;

    this.isAdmin = false;
  }

  getLoginToCart() {
    if (this.username === '' || this.username === null || this.username === undefined) {
      this.router.navigateByUrl('login');
    } else {
      this.router.navigateByUrl('cart');
    }
  }


  getLoginToHistoryCart() {
    if (this.username === '' || this.username === null || this.username === undefined) {
      this.router.navigateByUrl('login');
    } else {
      this.router.navigateByUrl('history');
    }
  }
}
