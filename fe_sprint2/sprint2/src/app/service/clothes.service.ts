import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICustomer} from '../model/i-customer';
import {IClothesDto} from '../model/i-clothes-dto';
import {DataResult} from '../model/data-result';

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
}
