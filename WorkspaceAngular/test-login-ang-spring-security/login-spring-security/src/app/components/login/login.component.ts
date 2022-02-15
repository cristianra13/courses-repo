import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../../models/user-login';
import { LoginService } from '../../services/login.service';
import { LoginResponse } from '../../models/login.response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  userLogin: UserLogin;
  loginResponse: LoginResponse;
  showErrorUser = false;
  showErrorPass = false;
  showErrorCredentials = false;

  constructor(private service: LoginService, private route: Router) { }

  ngOnInit(): void {
  }

  enviar()
  {
    this.clearError();

    if(this.username && this.password)
    {
      this.userLogin = new UserLogin(this.username, this.password);

      this.service.login(this.userLogin).subscribe( resp => {
        this.loginResponse = resp;
        this.showErrorCredentials = this.showErrorCredentials === true ? false : true;
        this.route.navigateByUrl('home');


      }, err => {
        if(err){
          this.loginResponse = err.error;
          if(this.loginResponse.status === 401){
            this.showErrorCredentials = true;
          }
        }
      });
    }
    else
    {
      if(!this.username)
      {
        this.showErrorUser = true;
      }
      if(!this.password){
        this.showErrorPass = true;
      }
    }
  }

  clearError(){
    this.showErrorUser = false;
    this.showErrorPass = false;
  }

}
