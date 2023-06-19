import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Feeder } from '../feeder';
import { ApiResponse } from '../apiResponse';

@Injectable({
  providedIn: 'root'
})
export class DataService{
  
  //private loginUrl = './assets/json/login.json';
  //private substationsUrl = './assets/json/substationList.json';
  private staticDataUrl = './assets/json/staticData.json';
  
  private fidderDetailsUrl = '/feeder-survey/api/v1/feeder-detail/search';
  private fiddersUrl = './assets/json/fidders.json';

  private substationsUrl = '/feeder-survey/api/v1/substations';
  private saveFeederDetailUrl = '/feeder-survey/api/v1/feeder-detail/save';
  
  private loginUrl = '/feeder-survey/api/v1/auth';
  private logoutUrl = '/feeder-survey/api/v1/logout';

  public existingTcCapacityList:any = [];
  public proposeTcCapacityList:any = [];
  public proposeRMUTypeList:any = [];

  public isAuthenticated = false;
  constructor( private http: HttpClient) { 
    this.fetchStaticData();
  }



  auth(username:string, password:string): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.loginUrl, {"userName": username, "pwd": password}).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }

  fetchSubstations(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.substationsUrl).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }
  fetchFidderDetails(fidder:Feeder): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.fidderDetailsUrl, fidder).pipe(//this.http.post<ApiResponse>(this.fidderDetailsUrl, fidder).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }

  fetchFidders(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.fiddersUrl).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }

  fetchStaticData() {
    return this.http.get<ApiResponse>(this.staticDataUrl).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    ).subscribe(response => {
      if (response.content != null) {
        this.existingTcCapacityList = response.content.existingTcCapacityList
        this.proposeTcCapacityList = response.content.proposeTcCapacityList
        this.proposeRMUTypeList = response.content.proposeRMUTypeList
      }
    })
  }

  saveFidderDetails(feedder: Feeder): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.saveFeederDetailUrl, feedder).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }
  logout(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.logoutUrl).pipe(
      tap(_ => this.log('auth call')),
      catchError(this.handleError<ApiResponse>('auth'))
    )
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    console.log(`DataService: ${message}`);
  }
}
