import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  apiEndpoint: string = "/api";
  constructor(private http: HttpClient) {
    // this.backend = environment.BACKEND;
    console.log(this.apiEndpoint);
  }

  ping() {
    return this.http.get(this.apiEndpoint + "/ping");
  }
  vote(user: string, id: number): Observable<String> {
    var form = new FormData();
    form.append('key', user);
    form.append('value', id.toString());
    return this.http.post<String>(`${this.apiEndpoint}/vote`, form);
  }
}
