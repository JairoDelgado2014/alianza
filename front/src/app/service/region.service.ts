import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Region } from '../model/region';

@Injectable({
  providedIn: 'root'
})
export class RegionService {

  constructor(private http: HttpClient) { }
  private urlBase = "http://localhost:9090/region/";

  getRegion() {
    return this.http.get<Region[]>(this.urlBase + "list");
  }
}
