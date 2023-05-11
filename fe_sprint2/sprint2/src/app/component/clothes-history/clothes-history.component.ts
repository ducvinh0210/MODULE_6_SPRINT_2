import {Component, OnInit} from '@angular/core';
import {ICart} from '../../model/i-cart';
import {TokenStorageService} from '../../service/token-storage.service';
import {ClothesService} from '../../service/clothes.service';
import {Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {BehaviorSubject, Observable} from 'rxjs';

@Component({
  selector: 'app-clothes-history',
  templateUrl: './clothes-history.component.html',
  styleUrls: ['./clothes-history.component.css']
})
export class ClothesHistoryComponent implements OnInit {
  page = 1;
  historyList$: Observable<ICart[]>;
  pageSize = 3;
  totalPage: number;

  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;


  constructor(private  tokenService: TokenStorageService,
              private clothesService: ClothesService,
              private router: Router,
              private title: Title) {
    title.setTitle('Lịch sử');
  }

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
    if (this.username !== '') {
      this.clothesService.findCustomer(this.username).subscribe(customer => {
        if (customer != null) {
          this.clothesService.getAllHistoryClothes(customer.id, this.page, this.pageSize).subscribe(value => {

            this.historyList$ = new BehaviorSubject<ICart[]>(value.content);
            console.log(this.historyList$);
            this.totalPage = Math.ceil(value.totalElements / this.pageSize);
            console.log(this.totalPage);


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

  previous(): void {
    this.page--;
    this.showUsername();
  }

  next(): void {
    this.page++;
    this.showUsername();
  }

  getPage1(): void {
    this.page = 1;
    this.showUsername();
  }

  getPageEnd(): void {
    this.page = this.totalPage;
    this.showUsername();
  }

  getPageP(): void {
    this.page -= 2;
    this.showUsername();
  }

  getPageN(): void {
    this.page += 2;
    this.showUsername();
  }


}




