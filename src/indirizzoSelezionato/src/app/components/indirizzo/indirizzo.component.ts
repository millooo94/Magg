/// <reference types="@types/google.maps" />

import { CommonModule } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  Output,
  ViewChild,
} from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'indirizzo',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <input
      class="input"
      type="text"
      [(ngModel)]="value"
      #addresstext
      style="margin: 0; padding: 0; box-sizing: border-box; border: 1px solid #ccc; width: 230px; height: 26px; border-radius: 4px; padding-left: 8px;"
    />
  `,
})
export class IndirizzoComponent {
  @Input() adressType!: string;
  @Input() value!: string | null | undefined;
  @Output() setAddress: EventEmitter<any> = new EventEmitter();
  @ViewChild('addresstext') addresstext: any;

  autocompleteInput!: string;
  queryWait!: boolean;

  constructor() {}

  ngOnInit() {}

  ngAfterViewInit() {
    this.getPlaceAutocomplete();
  }

  private getPlaceAutocomplete() {
    const autocomplete = new google.maps.places.Autocomplete(
      this.addresstext.nativeElement,
      {
        types: [this.adressType],
      }
    );
    google.maps.event.addListener(autocomplete, 'place_changed', () => {
      const place = autocomplete.getPlace();
      this.invokeEvent(place);
    });
  }

  invokeEvent(place: Object) {
    this.setAddress.emit(place);
  }
}
