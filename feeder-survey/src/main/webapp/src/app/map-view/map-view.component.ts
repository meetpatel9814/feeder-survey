import { HttpClient } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { catchError, map, Observable, of, Subscription } from 'rxjs';
import { Feeder } from '../feeder';
import { CommonService } from '../service/common.service';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})
export class MapViewComponent implements OnInit, OnDestroy  {
  subscription!: Subscription;
  fidderList:Feeder[] = [];

  apiLoaded: Observable<boolean>;
  markerOptions: google.maps.MarkerOptions[] = [];
  markerPositions: google.maps.LatLngLiteral[] = [];
  options: google.maps.MapOptions = {
    zoom: 12
  };
  postions:any[] = []

  constructor(private commonService: CommonService, httpClient: HttpClient)  {


    this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyAOCwndKAMIGbrVlnwbSN5DApnyL1yYoEg', 'callback')
      .pipe(
        map(() => true),
        catchError((error) => {
          console.log(error)
          return of(false)}),
      );
   }
  ngOnInit(): void {
    this.subscription = this.commonService.currentMessage.subscribe(itemList => this.newMethod(itemList))
    this.loadMap()
  }

  loadMap() {
    this.fidderList.forEach(item => {
      let latitude = Number.parseFloat(item.latitude);
      let longitude = Number.parseFloat(item.longitude);
      this.markerOptions.push({
        position: {lat: latitude, lng :longitude},
        label:  {
          text: item.transformerLocationName,
          color: "#203334",
          fontWeight: "bold",
          fontSize: "16px",
          className: "map-label"
        },
        clickable:true
      })
    })
    this.options.center = {lat: Number.parseFloat(this.fidderList[0].latitude), lng :Number.parseFloat(this.fidderList[0].longitude)}
  }

  private newMethod(itemList: any): void {
    this.fidderList = itemList;
  }
   ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
