import { Component, HostListener, inject, NgZone, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { IndirizzoService } from './services/indirizzo.service';

import { Cap, Indirizzo, Nazione, Provincia, Regione } from './models/index';
import Comune from './models/comune';
import { FormsModule } from '@angular/forms';
import { IndirizzoComponent } from './components/indirizzo/indirizzo.component';
import { DropdownComponent } from './components/dropdown/dropdown.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, FormsModule, IndirizzoComponent, DropdownComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  indirizzoService = inject(IndirizzoService);

  indirizzoSedePrincipale!: Indirizzo;
  sedePrincipaleInItaly: boolean = false;

  nazioni: Nazione[] = [];
  regioni: Regione[] = [];
  province: Provincia[] = [];
  comuni: Comune[] = [];
  cap: Cap[] = [];

  constructor(public zone: NgZone) {}

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    localStorage.setItem('windowClicked', 'false');
  }

  getAddress(place: object) {
    if (this.indirizzoSedePrincipale) {
      this.indirizzoSedePrincipale.indirizzo = (place as any)['formatted_address'];

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
        if (this.indirizzoSedePrincipale) {
          Object.assign(this.indirizzoSedePrincipale, updatedIndirizzo);
          this.sedePrincipaleInItaly = this.indirizzoSedePrincipale.nazione === "Italia";

          if (this.sedePrincipaleInItaly) {
            this.sedePrincipaleInItaly = true;

            let provinciaArray = this.indirizzoSedePrincipale.provincia!.split(" ")
            this.indirizzoSedePrincipale.provincia = provinciaArray[provinciaArray.length - 1];

            if (this.indirizzoSedePrincipale.provincia === "Capitale") {
              this.indirizzoSedePrincipale.provincia = "Roma";
            }

            this.getRegioni();
            this.getProvince(this.indirizzoSedePrincipale.regione);
            this.getComuni(this.indirizzoSedePrincipale.provincia);
            this.getCap(this.indirizzoSedePrincipale.comune);
          }
        }
      });
    }


    this.saveIndirizzoSedePrincipale(this.indirizzoSedePrincipale);
  }



  public ngOnInit(): void {
    this.getIndirizzoSedePrincipale();
  }

  public getIndirizzoSedePrincipale() {
    this.indirizzoService.getIndirizzoSedePrincipale().subscribe({
      next: (data) => {
        if (!!data) {
          this.indirizzoSedePrincipale = data;
        }
        this.getNazioni();
        if (this.indirizzoSedePrincipale?.nazione === "Italia") {
          this.sedePrincipaleInItaly = true;
          this.getRegioni();
          this.getProvince(this.indirizzoSedePrincipale!.regione);
          this.getComuni(this.indirizzoSedePrincipale!.provincia);
          this.getCap(this.indirizzoSedePrincipale!.comune);
        }
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
    this.sedePrincipaleInItaly = value === "Italia";
    this.indirizzoSedePrincipale!.nazione = value;

    if (this.sedePrincipaleInItaly) {
      this.indirizzoSedePrincipale!.indirizzo = null;
      this.indirizzoSedePrincipale!.regione = "(Nessuno)";
      this.indirizzoSedePrincipale!.provincia = "(Nessuno)";
      this.indirizzoSedePrincipale!.comune = "(Nessuno)";
      this.indirizzoSedePrincipale!.cap = "(Nessuno)";
      this.indirizzoSedePrincipale!.frazione = null;
    } else {
      this.indirizzoSedePrincipale!.indirizzo = null;
      this.indirizzoSedePrincipale!.regione = null;
      this.indirizzoSedePrincipale!.provincia = null;
      this.indirizzoSedePrincipale!.comune = null;
      this.indirizzoSedePrincipale!.cap = null;
      this.indirizzoSedePrincipale!.frazione = null;
    }
  }

  public onRegioneChange(value: string | null | undefined): void {
    this.indirizzoSedePrincipale!.regione = value;

    this.indirizzoSedePrincipale!.indirizzo = null;

    this.getProvince(this.indirizzoSedePrincipale!.regione);

    this.province = [];
    this.indirizzoSedePrincipale!.provincia = "(Nessuno)";
    this.comuni = [];
    this.indirizzoSedePrincipale!.comune = "(Nessuno)";
    this.cap = [];
    this.indirizzoSedePrincipale!.cap = "(Nessuno)";
    this.indirizzoSedePrincipale!.frazione = null;
  }

  public onProvinciaChange(value: string | null | undefined): void {
    this.indirizzoSedePrincipale!.provincia = value;

    this.indirizzoSedePrincipale!.indirizzo = null;

    this.getComuni(this.indirizzoSedePrincipale!.provincia)

    this.comuni = [];
    this.indirizzoSedePrincipale!.comune = "(Nessuno)";
    this.cap = [];
    this.indirizzoSedePrincipale!.cap = "(Nessuno)";
    this.indirizzoSedePrincipale!.frazione = null;
  }

  public onComuneChange(value: string | null | undefined): void {
    this.indirizzoSedePrincipale!.comune = value;

    this.indirizzoSedePrincipale!.indirizzo = null;

    this.getCap(this.indirizzoSedePrincipale!.comune)

    this.indirizzoSedePrincipale!.cap = "(Nessuno)";
    this.indirizzoSedePrincipale!.frazione = null;
  }


  public saveIndirizzoSedePrincipale(indirizzoSedePrincipale: Indirizzo) {
    this.indirizzoService
      .saveIndirizzoSedePrincipale(indirizzoSedePrincipale)
      .subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
