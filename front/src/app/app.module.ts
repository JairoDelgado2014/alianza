import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';

import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';

import { ClientService } from './service/client.service';
import { HttpClientModule } from '@angular/common/http';
import { DialogModule } from 'primeng/dialog';

import { TableModule } from 'primeng/table';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { routing } from './app-routing';
import { HomeComponent } from './home/home.component';
import { CountryComponent } from './country/country.component';
import { DropdownModule } from 'primeng/dropdown';



@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    HomeComponent,
    CountryComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    TableModule,
    DropdownModule,
    FormsModule,
    ConfirmDialogModule,
    HttpClientModule,
    DialogModule,
    ReactiveFormsModule,
  
  ],
  providers: [ClientService, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
