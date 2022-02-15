import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from './services/login.service';
import { Usuario } from './models/usuario.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit
{
  usuario: Usuario = new Usuario();

  constructor(private loginService: LoginService)
  {
    this.loginService.consulta().subscribe(data => console.log('Response: ', data));
  }

  ngOnInit(): void
  {
  }



  login(form: NgForm)
  {
    if(form.valid)
    {
      if(this.usuario != null)
      {
        this.loginService.login(this.usuario).subscribe(data => console.log());
      }
    }
  }
}
