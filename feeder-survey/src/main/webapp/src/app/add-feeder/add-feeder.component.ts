import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MapInfoWindow, MapMarker } from '@angular/google-maps';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { Feeder } from '../feeder';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-add-feeder',
  templateUrl: './add-feeder.component.html',
  styleUrls: ['./add-feeder.component.css']
})
export class AddFeederComponent implements OnInit{
  statusList = ['Active', 'Stopped']
  fidder: Feeder = new Feeder;
  substationList:any = []
  existingTcCapacityList:any = []
  proposeTcCapacityList:any = []
  proposeRMUTypeList:any = []
  feederList:any = []

  apiLoaded: Observable<boolean>;


  options: google.maps.MapOptions = {};
  infoWindowOption: google.maps.InfoWindowOptions = {};
  lng: any
  lat: any
  markerOptions: google.maps.MarkerOptions = {};
  markerPositions: google.maps.LatLngLiteral[] = [];
  
  
  @ViewChild(MapInfoWindow)
  infoWindow!: MapInfoWindow;

  constructor(
    private route: ActivatedRoute,
    private router: Router, httpClient: HttpClient, private dataService: DataService) {

    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyAOCwndKAMIGbrVlnwbSN5DApnyL1yYoEg', 'callback')
      .pipe(
        map(() => true),
        catchError((error) => {
          console.log(error)
          return of(false)
        }),
      );

    this.initMap()
  }
  ngOnInit(): void {
    this.fetchSubstationList()
    this.existingTcCapacityList = this.dataService.existingTcCapacityList
    this.proposeTcCapacityList = this.dataService.proposeTcCapacityList
    this.proposeRMUTypeList = this.dataService.proposeRMUTypeList
  }

  fetchSubstationList(){
    this.dataService.fetchSubstations().subscribe(
      response => {
        this.substationList = response.content
      }
    )
  }

/**
 * This method is used to set map content and set user current location
 */
  initMap(): void {
    if (navigator) {
      navigator.geolocation.getCurrentPosition(pos => {
        this.lng = pos.coords.longitude;
        this.lat = pos.coords.latitude;
        console.log(this.lat, "-", this.lng)
        this.options = {
          center: { lat: this.lat, lng: this.lng },
          zoom: 12
        };
        this.fidder.latitude= this.lat;
        this.fidder.longitude= this.lng;

        this.markerOptions = {
          position: { lat: this.lat, lng: this.lng },
          title: "Current Location"
        };
        this.markerPositions = [
          { lat: this.lat, lng: this.lng }
        ];
      });
    }
  }




  openInfoWindow(marker: MapMarker) {
    this.infoWindow.open(marker);
  }









  
  addMarker(event: google.maps.MapMouseEvent) {
    if (event.latLng != null) {

      console.log(event.latLng.toJSON());
    }

  }

  substationSelect(selectedSubstationName:string) {
    this.substationList.forEach((item: { id:number, name: string; feederList: any; }) => {
      if(this.fidder.substationId == item.id) {
        this.feederList= item.feederList;
        return
      }      
    });
  }



  saveFidderDetails() {
    console.log(this.fidder);
    this.dataService.saveFidderDetails(this.fidder).subscribe(
      response => {
        this.router.navigate(['/home']);
      }
    )
    
  }




}
