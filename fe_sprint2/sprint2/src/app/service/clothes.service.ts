import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ICustomer} from '../model/i-customer';
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
}
