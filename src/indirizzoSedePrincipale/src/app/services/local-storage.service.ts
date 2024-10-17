// local-storage.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LocalStorageService {
  private idAnagraficaSubject = new BehaviorSubject<string | null>(
    localStorage.getItem('idAnagrafica')
  );

  // Osservabile per ottenere il valore attuale di idAnagrafica
  idAnagrafica$ = this.idAnagraficaSubject.asObservable();

   // Subject per windowClicked
   private windowClickedSubject = new BehaviorSubject<string | null>(
    localStorage.getItem('windowClicked')
  );
  // Observable per ottenere il valore attuale di windowClicked
  windowClicked$ = this.windowClickedSubject.asObservable();

  constructor() {
    this.overrideLocalStorage();
    this.listenToStorageChanges();
  }

  private overrideLocalStorage() {
    const originalSetItem = localStorage.setItem;
    const originalRemoveItem = localStorage.removeItem;

    // Sovrascrivere il metodo setItem
    localStorage.setItem = (key: string, value: string) => {
      originalSetItem.call(localStorage, key, value); // Chiama il metodo originale
      if (key === 'idAnagrafica') {
        console.log('Nuovo valore impostato per idAnagrafica:', value);
        this.idAnagraficaSubject.next(value); // Emmetti il nuovo valore
      }

      if (key === 'windowClicked') {
        console.log('Nuovo valore impostato per windowClicked:', value);
        this.windowClickedSubject.next(value); // Emmetti il nuovo valore
      }
    };

    // Sovrascrivere il metodo removeItem
    localStorage.removeItem = (key: string) => {
      const value = localStorage.getItem(key); // Ottieni il valore prima di rimuoverlo
      originalRemoveItem.call(localStorage, key); // Chiama il metodo originale
      if (key === 'idAnagrafica') {
        console.log('idAnagrafica rimosso:', value);
        this.idAnagraficaSubject.next(null); // Emmetti null
      }
      if (key === 'windowClicked') {
        console.log('windowClicked rimosso:', value);
        this.windowClickedSubject.next(null); // Emmetti null
      }
    };
  }

  private listenToStorageChanges() {
    window.addEventListener('storage', (event) => {
      if (event.key === 'windowClicked') {
        console.log('Cambio rilevato in windowClicked:', event.newValue);
        this.windowClickedSubject.next(event.newValue);
      }
    });
  }
}
