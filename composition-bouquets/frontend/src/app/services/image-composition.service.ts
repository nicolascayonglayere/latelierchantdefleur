import { ImageComposition } from './../model/ImageComposition';
import { Observable } from 'rxjs';
import { HttpClient, HttpEvent, HttpRequest, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

const rootUrl = 'http://127.0.0.1:8181/atelier-chant-de-fleur/compositions/';

@Injectable({
  providedIn: 'root'
})
export class ImageCompositionService {

  constructor(private httpClient: HttpClient) { }

  getByIdComposition(id: number): Observable<ImageComposition[]>{
    return this.httpClient.get<ImageComposition[]>(rootUrl + id + '/images');
  }

  uploadImage(image: File, id: number, nom: string, descr: string): Observable<HttpEvent<any>>{
    const formData: FormData = new FormData();
    formData.append('file', image);
    let params: HttpParams = new HttpParams();
    params = params.append('nom', nom);
    params = params.append('description', descr);
    const req = new HttpRequest('POST', rootUrl + id + '/images/', formData, {
      reportProgress: true,
      params,
      responseType: 'json'
    });
    return this.httpClient.request(req);
  }

  getFiles(id: number): Observable<any> {
    return this.httpClient.get(rootUrl + id + '/images-content');
  }

  delete(idCompo: number, idImg: number): Observable<{}> {
    return this.httpClient.delete(rootUrl + idCompo + '/images/' + idImg);
  }
}
