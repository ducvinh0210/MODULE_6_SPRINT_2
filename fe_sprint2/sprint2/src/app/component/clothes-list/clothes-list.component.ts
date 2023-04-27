import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {IClothesDto} from '../../model/i-clothes-dto';
import {ClothesService} from '../../service/clothes.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-clothes-list',
  templateUrl: './clothes-list.component.html',
  styleUrls: ['./clothes-list.component.css']
})
export class ClothesListComponent implements OnInit {
  nameProduct = '';
  manufacturerProduct = '';
  typeProduct = '';
  priceStart = 0;
  priceEnd = 9999999;
  sortBy = 'newest';
  page = 1;
  pageSize = 6;
  quantity: number;
  clothesList$: Observable<IClothesDto[]>;
  totalPage: number;
  // shoeTypesList$: Observable<IType[]>;
  manufacturer$: Observable<string[]>;

  username: string;
  roles: string[] = [];
  isCustomer = false;
  isAdmin = false;






  constructor(private clothesService: ClothesService,
              private tokenService: TokenStorageService,
              private router: Router) { }

  ngOnInit(): void {
    this.getAllClothesPaging();
  }


  getAllClothesPaging(): void {
    this.clothesService.showListClothes(this.page, this.pageSize, this.nameProduct, this.manufacturerProduct, this.typeProduct,
      this.priceStart, this.priceEnd, this.sortBy).subscribe(value => {
        this.clothesList$ = new BehaviorSubject<IClothesDto[]>(value.content);
        this.quantity = value.totalElements;
        this.totalPage = Math.ceil(this.quantity / this.pageSize);
      },
      error => {
        console.log(error);
      });
  }
}
