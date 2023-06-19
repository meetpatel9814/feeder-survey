import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Feeder } from '../feeder';
import { CommonService } from '../service/common.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  public filterSection = true;
  fidderList:Feeder[] = [];
  fidder: Feeder = new Feeder;
  substationList:any = []
  existingTcCapacityList:any = []
  proposeTcCapacityList:any = []
  proposeRMUTypeList:any = []
  fidderFilterList:any = []

  constructor(  private route: ActivatedRoute, private router: Router, private dataService: DataService,
    private commonService: CommonService) {}
  ngOnInit(): void {
    this.fetchFidderDetails()
    this.fetchSubstationList()
    this.fetchFidders()
    this.existingTcCapacityList = this.dataService.existingTcCapacityList
    this.proposeTcCapacityList = this.dataService.proposeTcCapacityList
    this.proposeRMUTypeList = this.dataService.proposeRMUTypeList

    
  }
  

  fetchFidders() {
    this.dataService.fetchFidders().subscribe(
      response => {this.fidderFilterList = response.content
      }
    )
  }


  fetchFidderDetails(callback?:Function){
    this.dataService.fetchFidderDetails(this.fidder).subscribe(
      response => {
        this.fidderList = response.content
        this.commonService.changeFidderList(this.fidderList)
        this.fidder = new Feeder;
        if(callback) {
          callback.apply(undefined);
        }
      }
    )
  }

  fetchFidderDetailsWithSection() {
    this.fetchFidderDetails(() => this.filterSection = true)
  }

  fetchSubstationList(){
    this.dataService.fetchSubstations().subscribe(
      response => {
        this.substationList = response.content
      }
    )
  }
  openFidderViewScreen(fidder:any):void {
    console.log("clikc");
    this.router.navigate(['/fidder-view',  fidder.id ]);
  }

  showMapView(){
    this.router.navigate(['/map-view']);
  }

  logout(){
    this.dataService.logout().subscribe(
      (response) => {
        this.router.navigate(['/login']);
      }
    )
  }
  
}
