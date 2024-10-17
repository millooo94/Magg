import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { Cap, Indirizzo, Nazione, Provincia, Regione } from '../models';
import Comune from '../models/comune';
import { LocalStorageService } from './local-storage.service';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class IndirizzoService {
  private baseUrl: string = environment.apiUrl;

  private http = inject(HttpClient);
  localStorageService = inject(LocalStorageService);

  private idAnagrafica: number | undefined ;
  private idIndirizzoSelezionato: number | undefined;

  constructor() {
    this.localStorageService.idIndirizzoSelezionato$.subscribe((idIndirizzoSelezionato) => {
      console.log("SELECTEDDD => ", idIndirizzoSelezionato)
      this.idIndirizzoSelezionato = Number(idIndirizzoSelezionato);
    });
  }

  getIndirizzoSelezionato(): Observable<Indirizzo | null> {
    if (!!this.idIndirizzoSelezionato) {
      console.log("ID INDIRIZZO SELEZIONATO => ", this.idIndirizzoSelezionato)
      return this.http
        .get<Indirizzo>(`${this.baseUrl}/selectedIndirizzo/${this.idIndirizzoSelezionato}`)
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

  saveIndirizzoSelezionato(
    indirizzoSelezionato: Indirizzo
  ): Observable<Indirizzo> {
    if (indirizzoSelezionato.id !== null) {
      return this.http
        .put(
          `${this.baseUrl}/indirizzo/${indirizzoSelezionato.id}`,
          indirizzoSelezionato
        )
        .pipe(
          map((response) => response as Indirizzo),
          catchError(this.handleError)
        );
    } else {
      return this.http
        .post(`${this.baseUrl}/indirizzo`, indirizzoSelezionato)
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
}
