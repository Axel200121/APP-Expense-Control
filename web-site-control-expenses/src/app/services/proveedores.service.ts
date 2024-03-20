import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponseDto } from '../interface/api-response-dto';
import { environment } from '../../environments/environment.development';
import { ProveedoresDto } from '../interface/proveedores-dto';

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {

  constructor(private _http:HttpClient) { }


  public getAllProviders(token:string):Observable<any>{
    return this._http.get(`${environment.api}/v1/proveedores/get-all`,{headers:{'content-type':'application/json','Authorization':`Bearer ${token}`}});
  }

  public setProvider(providerDto:ProveedoresDto, token:string):Observable<any>{
    return this._http.post(`${environment.api}/v1/proveedores/save`,providerDto,{headers:
      {'content-type':'application/json','Authorization':`Bearer ${token}`}
    })
  }

  public updateProvider(id:any, providerDto:ProveedoresDto, token:string):Observable<any>{
    return this._http.put(`${environment.api}/v1/proveedores/update/${id}`,providerDto,{headers:
      {'content-type':'application/json','Authorization':`Bearer ${token}`}
    })
  }








}
