import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddFeederComponent } from './add-feeder/add-feeder.component';
import { AppComponent } from './app.component';
import { FeederViewComponent } from './feeder-view/feeder-view.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MapViewComponent } from './map-view/map-view.component';

const routes: Routes = [
  {path : 'home', component: HomeComponent},
  {path : 'login', component: LoginComponent},
  {path : 'fidder-view/:id', component: FeederViewComponent},
  {path : 'add-fidder', component: AddFeederComponent},
  {path : 'map-view', component: MapViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
