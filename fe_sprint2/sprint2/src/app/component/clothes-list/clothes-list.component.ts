import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {IClothesDto} from '../../model/i-clothes-dto';
import {ClothesService} from '../../service/clothes.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';
import {ClothesPage} from '../../dto/clothes-page';

@Component({
  selector: 'app-clothes-list',
  templateUrl: './clothes-list.component.html',
  styleUrls: ['./clothes-list.component.css']
})
export class ClothesListComponent implements OnInit {
  nameProduct = '';

  page = 0;
  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;
  clothesDto: IClothesDto[] = [];
  clothesPage!: ClothesPage;


  constructor(private clothesService: ClothesService,
              private tokenService: TokenStorageService,
              private router: Router) {
    this.listClothes();
  }

  ngOnInit(): void {

  }

  showUsername() {
    this.username = this.tokenService.getUser().username;
    // console.log(this.username);
    this.roles = this.tokenService.getUser().roles;
    this.isCustomer = this.roles.indexOf('ROLE_CUSTOMER') !== -1;
    this.isAdmin = this.roles.indexOf('ROLE_ADMIN') !== -1;
  }

  listClothes() {
    this.clothesService.showList(this.nameProduct.trim(), this.page).subscribe(value => {
      this.clothesDto = value.content;
      this.clothesPage = value;
    });
  }

  changePage(page: number) {
    this.page = page;
    this.listClothes();
    this.page = 0;
  }


}
