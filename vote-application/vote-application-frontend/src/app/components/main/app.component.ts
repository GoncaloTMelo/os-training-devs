import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { BackendService } from 'src/app/services/backend.service';
import { DatabaseService } from 'src/app/services/database.service';
import { Answer } from 'src/app/structures';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'vote-application-frontend';
  options: Answer[] = [
    {
      id: 0,
      label: "Teste_0"
    },
    {
      id: 1,
      label: "Teste_1"
    },
    {
      id: 2,
      label: "Teste_2"
    },
    {
      id: 3,
      label: "Teste_3"
    },
    {
      id: 4,
      label: "Teste_4"
    },
    {
      id: 5,
      label: "Teste_5"
    },
    {
      id: 6,
      label: "Teste_6"
    },
    {
      id: 7,
      label: "Teste_7"
    }
  ];
  optionsObservable: Observable<Answer[]>
  colorArray: string[] = [
    "color-0",
    "color-1",
    "color-2",
    "color-3",
    "color-4",
    "color-5",
    "color-6"
  ]

  userName: string = "";

  constructor(private backendService: BackendService, private databaseService: DatabaseService) {
    this.backendService.ping().subscribe((body) => console.log(body));
    this.optionsObservable = databaseService.getAllQuestions(); 

  }
  findColor(index: number): string {
    return this.colorArray[index % this.colorArray.length];
  }

  vote(option: Answer): void {
    let userName;
    if (!this.userName || this.userName.trim().length == 0) {
      alert("No Username inserted");
      return;
    }
    userName = this.userName.trim();
    console.log(`User ${this.userName} is voting for: ${JSON.stringify(option)}`);
    this.backendService.vote(userName, option.id).subscribe({
      next: (v: String) => console.log(v),
      error: (e: HttpErrorResponse) => { console.error(e); alert(e.message) },
      complete: () => console.info('complete')
    });
  }
}
