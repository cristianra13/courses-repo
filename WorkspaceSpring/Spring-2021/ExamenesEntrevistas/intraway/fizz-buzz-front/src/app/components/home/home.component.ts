import { Component, OnInit } from '@angular/core';
import { FizzBuzzModel } from '../../models/fizz-buzz.model';
import { FizzBuzzService } from '../../service/fizz-buzz.service';
import { FizzBuzzResponse } from '../../models/fizz-buzz-response.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit {
  showAlert: boolean = false;
  showAlertMin: boolean = false;
  showAlertMax: boolean = false;
  listFizzBuzz: FizzBuzzResponse[] = [];
  public selectedFizzBuzz: FizzBuzzResponse;

  constructor(private serviceRegister: FizzBuzzService) { }

  ngOnInit(): void {
    this.updateListFizzBuzz();
  }

  sendData(fizzBuzz: FizzBuzzModel) {
    this.validateValuesMinMax(fizzBuzz);
    if (fizzBuzz.min > fizzBuzz.max) {
      this.showAlert = true;
      return;
    }
    this.serviceRegister.registerFizzBuzz(fizzBuzz).subscribe(resp => {
      this.listFizzBuzz.push(resp);
    });
  }

  updateListFizzBuzz() {
    this.serviceRegister.listAllFizzBuzzRegisters().subscribe(resp => {
      this.listFizzBuzz = resp;
    })
  }

  onShowModalInfo(fizzBuzz: FizzBuzzResponse) {
    this.selectedFizzBuzz = fizzBuzz;
    this.clickButton('openUserInfo');
  }

  private validateValuesMinMax(fizzBuzz: FizzBuzzModel) {
    console.log(fizzBuzz.min);
    if (!fizzBuzz.min) {
      this.showAlertMin = true
    }
    if (!fizzBuzz.max) {
      this.showAlertMax = true
    }
  }

  private clickButton(buttonId: string): void {
    document.getElementById(buttonId).click();
  }
}
