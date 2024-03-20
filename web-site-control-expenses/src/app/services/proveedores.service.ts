import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDto } from '../interface/api-response-dto';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {

  constructor(private _http:HttpClient) { }


  public getAllProviders(token:string):Observable<any>{
    return this._http.get(`${environment.api}/v1/proveedores/get-all`,{headers:{'content-type':'application/json','Authorization':`Bearer ${token}`}});
  }





}
