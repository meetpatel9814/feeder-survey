import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  dataService:DataService;
  username!:string
  password!:string
  wrongPassword = false
  constructor (dataService: DataService, private router: Router) {
    this.dataService= dataService;
  }

  login() {
    this.wrongPassword = false
    this.dataService.auth(this.username, this.password).subscribe(
      response => {
        if (response && Number.parseInt(response.status) == 200) {
          this.router.navigate(['/home']);
          this.wrongPassword = false
        } else {
          this.wrongPassword = true
        }
      }
    )
  }
}
