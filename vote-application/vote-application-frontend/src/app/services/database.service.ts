import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Answer } from '../structures';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {
  apiEndpoint: string = "/db";

  constructor(private http: HttpClient) {
    this.apiEndpoint = environment.DATABASE;
    console.log(this.apiEndpoint);
  }

  getAllQuestions(): Observable<Answer[]> {
    return this.http.get<Answer[]>(this.apiEndpoint + "/answers");
  }
}
