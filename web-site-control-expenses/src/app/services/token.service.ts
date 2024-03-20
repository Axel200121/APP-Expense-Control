import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { LoginDto } from '../interface/login-dto';
import { ApiResponseDto } from '../interface/api-response-dto';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private _http:HttpClient) { }

  
  public login(loginDto:LoginDto):Observable<any>{
    console.log(loginDto)
    return this._http.post(`${environment.api}/auth/login`,loginDto,{headers:{'content-type':'application/json'}})
  }

}
