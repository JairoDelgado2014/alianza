import { RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { ClientComponent } from './client/client.component';


const appRoutes = [
  { path: 'client', component: ClientComponent,  pathMatch: 'full'},
];
export const routing = RouterModule.forRoot(appRoutes);