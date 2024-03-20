import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { UserDto } from '../interface/user-dto';
import { ApiResponseDto } from '../interface/api-response-dto';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(private _http:HttpClient) { }

  public getAllUsers(token:string):Observable<ApiResponseDto>{
    return this._http.get<ApiResponseDto>(`${environment.api}/v1/usuario/get-all`,{headers:
      {'content-type':'application/json','Authorization':`Bearer ${token}`}
    })
  }

  public getUserById(id:string, token:string):Observable<any>{
    return this._http.get(`${environment.api}/v1/usuario/get-user/${id}`,{headers:
      {'content-type':'application/json','Authorization':`Bearer ${token}`}
    })
  }

  public ssaveRegister(UserDto:UserDto, token:string):Observable<any>{
    return this._http.post(`${environment.api}/auth/register`, UserDto, {headers:
      {'content-type':'application/json','Authorization':`Bearer ${token}`}
    })
  }


}
