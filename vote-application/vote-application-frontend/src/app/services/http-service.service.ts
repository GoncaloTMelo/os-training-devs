import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) {
    console.log(environment.BACKEND);
  }

  ping() {
    return this.http.get(environment.BACKEND + "/ping");
  }
  vote(user: string, id: number): Observable<String> {
    var form = new FormData();
    form.append('key', user);
    form.append('value', id.toString());
    return this.http.post<String>(`${environment.BACKEND}/vote`, form);
  }
}
