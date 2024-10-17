import { Component, HostListener, inject, NgZone, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { IndirizzoService } from './services/indirizzo.service';

import { Cap, Indirizzo, Nazione, Provincia, Regione } from './models/index';
import Comune from './models/comune';
import { FormsModule } from '@angular/forms';
import { IndirizzoComponent } from './components/indirizzo/indirizzo.component';
import { DropdownComponent } from './components/dropdown/dropdown.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, IndirizzoComponent, DropdownComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit, OnDestroy {
  indirizzoService = inject(IndirizzoService);

  indirizzoSelezionato!: Indirizzo;
  indirizzoSelezionatoInItaly: boolean = false;

  nazioni: Nazione[] = [];
  regioni: Regione[] = [];
  province: Provincia[] = [];
  comuni: Comune[] = [];
  cap: Cap[] = [];

  private subscriptions: Subscription[] = [];

  constructor(public zone: NgZone) {
    this.listenToIndirizzoChanges();
  }


  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    localStorage.setItem('windowClicked', 'false');
  }

  getAddress(place: object) {
    if (this.indirizzoSelezionato) {
      this.indirizzoSelezionato.indirizzo = (place as any)['formatted_address'];

      const updatedIndirizzo: Partial<Indirizzo> = {};

      for (const item of (place as any)['address_components']) {
        if (item.types.includes("country")) {
          updatedIndirizzo.nazione = item['long_name'];
        }

        if (item.types.includes("administrative_area_level_1")) {
          updatedIndirizzo.regione = item['long_name'];
        }

        if (item.types.includes("administrative_area_level_2")) {
            updatedIndirizzo.provincia = item['long_name'];

        }

        if (
          item.types.includes("administrative_area_level_3") ||
          item.types.includes("locality")
        ) {
          updatedIndirizzo.comune = item['long_name'];
        }

        if (item.types.includes("postal_code")) {
          updatedIndirizzo.cap = item['long_name'];
        } else {
          updatedIndirizzo.cap = null;
        }
      }


      this.zone.run(() => {
        if (this.indirizzoSelezionato) {
          Object.assign(this.indirizzoSelezionato, updatedIndirizzo);
          this.indirizzoSelezionatoInItaly = this.indirizzoSelezionato.nazione === "Italia";

          if (this.indirizzoSelezionatoInItaly) {
            this.indirizzoSelezionatoInItaly = true;

            let provinciaArray = this.indirizzoSelezionato.provincia!.split(" ")
            this.indirizzoSelezionato.provincia = provinciaArray[provinciaArray.length - 1];

            if (this.indirizzoSelezionato.provincia === "Capitale") {
              this.indirizzoSelezionato.provincia = "Roma";
            }

            this.getRegioni();
            this.getProvince(this.indirizzoSelezionato.regione);
            this.getComuni(this.indirizzoSelezionato.provincia);
            this.getCap(this.indirizzoSelezionato.comune);
          }
        }
      });
    }


    this.saveIndirizzoSelezionato(this.indirizzoSelezionato);
  }



  public ngOnInit(): void {
    this.getIndirizzoSelezionato();
  }

  public getIndirizzoSelezionato() {
    this.indirizzoService.getIndirizzoSelezionato().subscribe({
      next: (data) => {
        if (!!data) {
          this.zone.run(() => {
            this.indirizzoSelezionato = data;
            console.log("indirizzo minchia selezionato ", this.indirizzoSelezionato);
            if (this.indirizzoSelezionato?.nazione === "Italia") {
              this.indirizzoSelezionatoInItaly = true;
              this.getRegioni();
              this.getProvince(this.indirizzoSelezionato!.regione);
              this.getComuni(this.indirizzoSelezionato!.provincia);
              this.getCap(this.indirizzoSelezionato!.comune);
            }
          });
        }
        this.getNazioni();
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  public getNazioni(): void {
    this.indirizzoService.getNazioni().subscribe({
      next: (data) => {
        this.nazioni = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  public getRegioni(): void {
    this.indirizzoService.getRegioni().subscribe({
      next: (data) => {
        this.regioni = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  public getProvince(regione: string | undefined | null): void {
    this.indirizzoService.getProvince(regione).subscribe({
      next: (data) => {
        this.province = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  public getComuni(provincia: string | undefined | null): void {
    this.indirizzoService.getComuni(provincia).subscribe({
      next: (data) => {
        this.comuni = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
  public getCap(comune: string | undefined | null): void {
    this.indirizzoService.getCap(comune).subscribe({
      next: (data) => {
        this.cap = data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  public onNazioneChange(value: string | null | undefined): void {
    this.indirizzoSelezionatoInItaly = value === "Italia";
    this.indirizzoSelezionato!.nazione = value;

    if (this.indirizzoSelezionatoInItaly) {
      this.indirizzoSelezionato!.indirizzo = null;
      this.indirizzoSelezionato!.regione = "(Nessuno)";
      this.indirizzoSelezionato!.provincia = "(Nessuno)";
      this.indirizzoSelezionato!.comune = "(Nessuno)";
      this.indirizzoSelezionato!.cap = "(Nessuno)";
      this.indirizzoSelezionato!.frazione = null;
    } else {
      this.indirizzoSelezionato!.indirizzo = null;
      this.indirizzoSelezionato!.regione = null;
      this.indirizzoSelezionato!.provincia = null;
      this.indirizzoSelezionato!.comune = null;
      this.indirizzoSelezionato!.cap = null;
      this.indirizzoSelezionato!.frazione = null;
    }
  }

  public onRegioneChange(value: string | null | undefined): void {
    this.indirizzoSelezionato!.regione = value;

    this.indirizzoSelezionato!.indirizzo = null;

    this.getProvince(this.indirizzoSelezionato!.regione);

    this.province = [];
    this.indirizzoSelezionato!.provincia = "(Nessuno)";
    this.comuni = [];
    this.indirizzoSelezionato!.comune = "(Nessuno)";
    this.cap = [];
    this.indirizzoSelezionato!.cap = "(Nessuno)";
    this.indirizzoSelezionato!.frazione = null;
  }

  public onProvinciaChange(value: string | null | undefined): void {
    this.indirizzoSelezionato!.provincia = value;

    this.indirizzoSelezionato!.indirizzo = null;

    this.getComuni(this.indirizzoSelezionato!.provincia)

    this.comuni = [];
    this.indirizzoSelezionato!.comune = "(Nessuno)";
    this.cap = [];
    this.indirizzoSelezionato!.cap = "(Nessuno)";
    this.indirizzoSelezionato!.frazione = null;
  }

  public onComuneChange(value: string | null | undefined): void {
    this.indirizzoSelezionato!.comune = value;

    this.indirizzoSelezionato!.indirizzo = null;

    this.getCap(this.indirizzoSelezionato!.comune)

    this.indirizzoSelezionato!.cap = "(Nessuno)";
    this.indirizzoSelezionato!.frazione = null;
  }


  public saveIndirizzoSelezionato(indirizzoSelezionato: Indirizzo) {
    this.indirizzoService
      .saveIndirizzoSelezionato(indirizzoSelezionato)
      .subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }

  private listenToIndirizzoChanges() {
    const sub = this.indirizzoService.localStorageService.idIndirizzoSelezionato$.subscribe((id) => {
      if (id) {
        this.getIndirizzoSelezionato();
      }
    });

    this.subscriptions.push(sub);
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach((sub) => sub.unsubscribe());
  }
}
