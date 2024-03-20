import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private _cookieService:CookieService) { }

  public validateAuth(){
    if(!this._cookieService.check('token_expenses_control')){
      window.location.href="/"
    }else if (!this._cookieService.check('name_expenses_control')) {
      window.location.href="/"
    }else if (!this._cookieService.check('idUser_expenses_control')) {
      window.location.href="/"
    }else if (!this._cookieService.check('idPerfil_expenses_control')) {
      window.location.href="/"
    }else if (!this._cookieService.check('perfil_expenses_control')) {
      window.location.href="/"
    }
  }
  

  
}
