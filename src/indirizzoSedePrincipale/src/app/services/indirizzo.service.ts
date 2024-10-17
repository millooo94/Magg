import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { Cap, Indirizzo, Nazione, Provincia, Regione } from '../models';
import Comune from '../models/comune';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class IndirizzoService {
  private baseUrl: string = '';

  private http = inject(HttpClient);
  localStorageService = inject(LocalStorageService);

  private idAnagrafica: number | undefined ;

  constructor() {

    this.loadConfig().subscribe(config => {
      this.baseUrl = `${config.PROTOCOL}://${config.SERVER}:${config.PORT}`;
      console.log("Base URL: ", this.baseUrl);
    });

    this.localStorageService.idAnagrafica$.subscribe((idAnagrafica) => {
      this.idAnagrafica = Number(idAnagrafica);
    });
  }

  getIndirizzoSedePrincipale(): Observable<Indirizzo | null> {
    if (!!this.idAnagrafica) {
      console.log(this.idAnagrafica)
      return this.http
        .get<Indirizzo>(`${this.baseUrl}/indirizzo/${this.idAnagrafica}`)
        .pipe(
          map((response) => response),
          catchError(this.handleError)
        );
    } else {
      return of(null);
    }
  }

  getNazioni(): Observable<Nazione[]> {
    return this.http.get<Nazione[]>(`${this.baseUrl}/nazioni`).pipe(
      map((response) => response),
      catchError(this.handleError)
    );
  }
  getRegioni(): Observable<Regione[]> {
    return this.http.get<Regione[]>(`${this.baseUrl}/regioni`).pipe(
      map((response) => response),
      catchError(this.handleError)
    );
  }
  getProvince(regione: string | undefined | null): Observable<Provincia[]> {
    return this.http
      .get<Provincia[]>(`${this.baseUrl}/province/${regione}`)
      .pipe(
        map((response) => response),
        catchError(this.handleError)
      );
  }
  getComuni(provincia: string | undefined | null): Observable<Comune[]> {
    return this.http.get<Comune[]>(`${this.baseUrl}/comuni/${provincia} `).pipe(
      map((response) => response),
      catchError(this.handleError)
    );
  }
  getCap(comune: string | undefined | null): Observable<Cap[]> {
    return this.http.get<Cap[]>(`${this.baseUrl}/cap/${comune}`).pipe(
      map((response) => response),
      catchError(this.handleError)
    );
  }

  saveIndirizzoSedePrincipale(
    indirizzoSedePrincipale: Indirizzo
  ): Observable<Indirizzo> {
    if (indirizzoSedePrincipale.id !== null) {
      return this.http
        .put(
          `${this.baseUrl}/indirizzo/${indirizzoSedePrincipale.id}`,
          indirizzoSedePrincipale
        )
        .pipe(
          map((response) => response as Indirizzo),
          catchError(this.handleError)
        );
    } else {
      return this.http
        .post(`${this.baseUrl}/indirizzo`, indirizzoSedePrincipale)
        .pipe(
          map((response) => response as Indirizzo),
          catchError(this.handleError)
        );
    }
  }

  private handleError(error: any) {
    console.log(error);
    return throwError(() => new Error('Something went wrong'));
  }


  private loadConfig(): Observable<any> {
    return this.http.get<any>('C:/TEMP/config.json').pipe(
      catchError(this.handleError)
    );
  }
}
