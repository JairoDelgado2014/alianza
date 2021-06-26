import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Country } from '../model/country';

@Injectable({
  providedIn: 'root'
})
export class CountryService {


  constructor(private http: HttpClient) { }
  private urlBase = "http://localhost:9090/country/";

  getCountryRegion(id: Number) {
    return this.http.get<Country[]>(this.urlBase + "listcountryregion/" + id);
  }
}
