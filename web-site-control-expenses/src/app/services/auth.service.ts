import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import swal from 'sweetalert2';

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


  public getToken(){
    return this._cookieService.get('token_expenses_control')
  }

  public getName(){
    return this._cookieService.get('name_expenses_control')
  }


  public getIduser(){
    return this._cookieService.get('idUser_expenses_control')
  }

  public getIdPerfil(){
    return this._cookieService.get('idPerfil_expenses_control')
  }

  public getPerfil(){
    return this._cookieService.get('perfil_expenses_control')
  }

  public logout(){
    swal.fire({
      position:'top-end',
      title:'¿Realmente desea cerrar la sesión?',
      icon:'warning',
      showCancelButton:true,
      confirmButtonColor:'#3085d6',
      cancelButtonColor:'#d33',
      cancelButtonText:'No',
      confirmButtonText:'Si'
    }).then(async (result)=>{
      if(result.isConfirmed){
        this._cookieService.deleteAll()
         window.location.href="/"
      }
    })
  }

  

  
}
