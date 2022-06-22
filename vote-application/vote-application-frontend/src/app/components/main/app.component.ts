import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';

interface VotingOption {
  id: number,
  label: string
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'vote-application-frontend';
  options: VotingOption[] = [
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

  constructor(private httpService: HttpServiceService) {
    this.httpService.ping().subscribe((body) => console.log(body));

  }
  findColor(index: number): string {
    let currentIndex = index;
    while (currentIndex >= this.colorArray.length)
      currentIndex = currentIndex - this.colorArray.length
    return this.colorArray[currentIndex];
  }

  vote(option: VotingOption): void {
    let userName;
    if (!this.userName || this.userName.trim().length == 0) {
      alert("No Username inserted");
      return;
    }
    userName = this.userName.trim();
    console.log(`User ${this.userName} is voting for: ${JSON.stringify(option)}`);
  }
}
