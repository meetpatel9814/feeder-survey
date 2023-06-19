import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Feeder } from '../feeder';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  public fidderList:Feeder[] = [];
  private messageSource = new BehaviorSubject(this.fidderList);
  currentMessage = this.messageSource.asObservable();

  constructor() { }

  changeFidderList(fidderList: any) {
    this.messageSource.next(fidderList)
  }

}
