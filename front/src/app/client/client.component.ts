import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { Cliente } from '../client';
import { ClientService } from '../service/client.service';
import { Clientvo } from '../clientvo';


@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  user!: FormGroup;
  display?: boolean;

  search: string = "";
  searchVisible: boolean = false;

  id?: Number;
  shareKey?: String;
  businessId?: String;
  email?: String;
  phone?: String;
  dateAdd?: Date;
  dateStart?: Date;
  dateEnd?: Date;
  estado?: Number;
  cliente = new Cliente;
  clientevo = new Clientvo;
  acction_?: String;
  editVisible?: boolean = false;
  acctionLabel?: String = "Add New Client";
  clientes: Cliente[] = [];
  prueba: Cliente[] = [];
  constructor(private router: Router, private serviceClient: ClientService, private confirmationService: ConfirmationService) {
  }

  ngOnInit(): void {
    this.listar();
    this.user = new FormGroup({
      shareKey: new FormControl('', [Validators.required, Validators.minLength(5)]),
      businessId: new FormControl('', [Validators.required, Validators.minLength(5)]),
      phone: new FormControl('', [Validators.required, Validators.minLength(7), Validators.maxLength(10)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      date: new FormControl('', [Validators.required]),
      id: new FormControl(''),
    });
  }

  /*shareKeyControl() {
    this.shareKey.valueChanges.pipe(debounceTime(350)).subscribe(data => {
      console.log(data);
    })
  }*/

  listar() {
    this.serviceClient.getAll().subscribe(data => {
      this.clientes = data;
    });
  }

  delete(id: number) {
    this.serviceClient.delete(id).subscribe(data => {
      if (data == true) {
        this.listar();
        console.log("Eliminado con Éxito");
      } else {
        console.log("Error al eliminar");
      }
    });
  }

  loadForm(id: number) {
    this.acction_ = "edit";
    this.acctionLabel = "Edit Client";
    this.editVisible = true;
    this.newClient();
    this.serviceClient.getbyid(id).subscribe(data => {
      this.shareKey = data.shareKey;
      this.businessId = data.businessId;
      this.phone = data.phone;
      this.email = data.email;
      this.dateAdd = data.dateAdd;
      this.id = data.id;
    })
    this.showDialog();
  }

  cleanForm() {
    this.shareKey = "";
    this.businessId = "";
    this.phone = "";
    this.email = "";
    this.dateAdd = new Date();
    this.estado = 0;
  }

  saveClient(acction?: String) {
    this.cliente.shareKey = this.shareKey;
    this.cliente.businessId = this.businessId;
    this.cliente.phone = this.phone;
    this.cliente.email = this.email;
    this.cliente.dateAdd = this.dateAdd;
    this.cliente.estado = 1;
    if (acction != "edit") {
      this.serviceClient.createClient(this.cliente).subscribe(data => {
        if (data != null) {
          this.newClient();
          this.closeDialog();
          this.listar();
        } else {
          this.closeDialog();
        }
      });
    } else {
      this.cliente.id = this.id;
      this.serviceClient.editClient(this.cliente).subscribe(data => {
        if (data != null) {
          this.newClient();
          this.closeDialog();
          this.listar();
        } else {
          this.closeDialog();
        }
      });
    }
    this.acction_ = "";
    this.acctionLabel = "Add New Client";
    this.editVisible = false;
  }

  showDialog() {
    if (this.acction_ != 'edit') {
      this.cleanForm();
      let fecha = new Date();
      this.dateAdd = fecha;
    }
    this.display = true;
  }

  closeDialog() {
    this.newClient();
    this.display = false;
  }

  newClient() {
    this.cliente = new Cliente;
  }

  addClient() {
    this.user.reset();
    this.acctionLabel = "Add New Client";
    this.showDialog();
  }

  confirm(id: number) {
    this.confirmationService.confirm({
      message: '¿ You really want to delete the Client?',
      accept: () => {
        this.delete(id);
      }
    });
  }

  searchClient() {
    let cadena = this.search;
    if (cadena != '') {
      this.serviceClient.getShareKey(cadena).subscribe(data => {
        this.clientes = data;
      })
    } else {
      this.listar();
    }
  }

  searchAdvance() {
    this.searchVisible = true;
    this.acctionLabel = "Serach Advance";
    this.user.reset();
    this.showDialog();
  }

  searchAdvanceServicio() {
    this.clientevo.shareKey = this.shareKey;
    this.clientevo.phone = this.phone;
    this.clientevo.email = this.email;
    this.clientevo.dateStart = this.dateStart;
    this.clientevo.dateEnd = this.dateEnd;
    this.serviceClient.searchAdvance(this.clientevo).subscribe(data => {
      this.prueba.push(data);    
    })
    this.carga();   
  }

  carga() {    
    this.prueba.length;
    for (let i = 0; i < this.prueba.length; i++) {
      this.clientes.push(this.prueba[i]);
    }
  }
}
