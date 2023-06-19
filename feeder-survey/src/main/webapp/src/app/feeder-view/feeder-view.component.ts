import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';

@Component({
  selector: 'app-feeder-view',
  templateUrl: './feeder-view.component.html',
  styleUrls: ['./feeder-view.component.css']
})
export class FeederViewComponent implements OnInit{
  selectedFidderId:any;
  apiLoaded: Observable<boolean>;
  locations = [
    ['Los Angeles', 34.052235, -118.243683],
    ['Santa Monica', 34.024212, -118.496475],
    ['Redondo Beach', 33.849182, -118.388405],
    ['Newport Beach', 33.628342, -117.927933],
    ['Long Beach', 33.770050, -118.193739]
  ];
  options: google.maps.MapOptions = {
    center: {lat: 40, lng: -20},
    zoom: 4
  };

  markerOptions: google.maps.MarkerOptions = {draggable: false};
  markerPositions: google.maps.LatLngLiteral[] = [];


  constructor(
    private route: ActivatedRoute,
    private router: Router, httpClient: HttpClient) {

      this.apiLoaded = httpClient.jsonp('https://maps.googleapis.com/maps/api/js?key=AIzaSyAOCwndKAMIGbrVlnwbSN5DApnyL1yYoEg', 'callback')
      .pipe(
        map(() => true),
        catchError((error) => {
          console.log(error)
          return of(false)}),
      );

    }

  ngOnInit(): void {
    this.selectedFidderId = this.route.snapshot.paramMap.get('id');
    this.loadData()
  }
  loadData() {
    this.markerOptions = {
      position: {lat: 34.052235, lng :-118.243683},
      label: 'Jasmin fidder'
    }
      this.markerPositions = [{lat: 34.052235, lng :-118.243683}]
      this.options.center = {lat: 34.052235, lng :-118.243683}
    
    
  }

  addMarker(event: google.maps.MapMouseEvent) {
    if(event.latLng != null) {

      this.markerPositions.push(event.latLng.toJSON());
    }
    
  }
}
