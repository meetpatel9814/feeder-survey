import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from './service/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  
  title = 'fidder-finder';
  constructor(  private route: ActivatedRoute, private router: Router, private dataService : DataService) {}
  ngOnInit(): void {
    if(this.dataService.isAuthenticated) {
      this.router.navigate(['home'], { relativeTo: this.route });
    } else {
      this.router.navigate(['login'], { relativeTo: this.route });
    }

    
  }
  
}
