import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICustomer} from '../model/i-customer';
import {IClothesDto} from '../model/i-clothes-dto';
import {DataResult} from '../model/data-result';
import {ICart} from '../model/i-cart';


const API_URL = 'http://localhost:8080/api';


@Injectable({
  providedIn: 'root'
})


export class ClothesService {


  constructor(private httpClient: HttpClient) {
  }

  findCustomer(username: string): Observable<ICustomer> {
    return this.httpClient.get<ICustomer>(API_URL + '/clothes/get-customer/' + username);
  }


  showListClothes(curPage: number, numberRecord: number, nameProduct: string, manufacturerProduct: string, typeProduct: string,
                  priceStart: any, priceEnd: any, sortBy: string): Observable<DataResult<IClothesDto>> {
    return this.httpClient.get<DataResult<IClothesDto>>(API_URL + '/clothes/list-' + sortBy + '/' + nameProduct + '&' + manufacturerProduct
      + '&' + typeProduct + '&' + priceStart + '&' + priceEnd + '?page=' + (curPage - 1) + '&size=' + numberRecord);
  }

  showList(nameProduct: string, pageNumber: any): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/clothes/list?nameProduct=' + nameProduct + '&page=' + pageNumber);
  }

  showListClothesNewest(nameProduct: string, pageNumber: any): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/clothes/list-newest?nameProduct=' + nameProduct + '&page=' + pageNumber);
  }

  showListClothesPriceAsc(nameProduct: string, pageNumber: any): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/clothes/list-price-asc?nameProduct=' + nameProduct + '&page=' + pageNumber);
  }

  findCartByUser(id: number): Observable<ICart[]> {
    return this.httpClient.get<ICart[]>(API_URL + '/clothes/cart/' + id);
  }

  paymentClothes(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/clothes/payment-clothes/' + id);
  }

  setQuantityProduct(id: number): Observable<any> {

    return this.httpClient.get<any>(API_URL + '/clothes/update-quantity-product?customerId=' + id);
  }

  findClothesById(id: number): Observable<IClothesDto> {
    return this.httpClient.get<IClothesDto>(API_URL + '/clothes/detail/' + id);
  }


  addToCart(quantity: number, customerId: number, productSizeId: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/clothes/add-cart/' + quantity + '&' + customerId + '&' + productSizeId);
  }


  findAllSizeByClothes(id: number): Observable<IClothesDto[]> {
    return this.httpClient.get<IClothesDto[]>(API_URL + '/clothes/clothes-size/' + id);
  }

  descQuantityCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/clothes/desc-quantity/' + id);
  }

  ascQuantityCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/clothes/asc-quantity/' + id);
  }

  removeCart(id: number): Observable<void> {
    return this.httpClient.get<void>(API_URL + '/clothes/remove-cart/' + id);
  }

  getAllHistoryClothes(id: number, curPage: number, numberRecord): Observable<DataResult<ICart>> {
    return this.httpClient.get<DataResult<ICart>>(API_URL + '/clothes/history-cart/' + id + '?page=' + (curPage - 1) + '&size='
      + numberRecord);
  }
}
