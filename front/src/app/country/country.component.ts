import { Component, OnInit } from '@angular/core';
import { CountryService } from '../service/country.service';
import { Country } from '../model/country';
import { RegionService } from '../service/region.service';
import { Region } from '../model/region';

@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent implements OnInit {
  country: Country[] = [];
  region: Region[] = [];
  /////////////

  selectedState: any = null;


  constructor(private countryService: CountryService, private regionService: RegionService) { }

  ngOnInit(): void {
    this.regionList();
  }

  countryRegion(id: number) {
    this.countryService.getCountryRegion(id).subscribe(data => {
      console.log(data);
      this.country = data;
    })
  }

  regionList() {
    this.regionService.getRegion().subscribe(data => {
      console.log(data);
      this.region = data;
    })
  }

  serarch() {
    console.log(this.selectedState);
    this.countryRegion(this.selectedState.id);
  }

}
