import { CommonModule } from '@angular/common';
import { Component, ElementRef, EventEmitter, HostListener, inject, Input, Output, SimpleChanges, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LocalStorageService } from '../../services/local-storage.service';

@Component({
  selector: 'dropdown',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.scss'
})
export class DropdownComponent {

  localStorageService = inject(LocalStorageService);

  @Input()
  items!: any[];

  @Input()
  key!: string;

  @Input()
  value: string | undefined | null = "(Nessuno)"

  @Output()
  onchange: EventEmitter<string | undefined | null> = new EventEmitter<string | undefined | null>()


  elementRef = inject(ElementRef);

  @ViewChild('autocompleteInput') autocompleteInput!: ElementRef;

  @HostListener('document:click', ['$event'])
  clickOut(event: Event) {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.open = false;
      this.value = this.autocompleteInput.nativeElement.value;
    }
  }

  public open: boolean = false;


  filteredItems: string[] = [];
  previousValue: string = '';


  constructor() {
    this.localStorageService.windowClicked$.subscribe((value) => {
      console.log("VALORE => ", value)
      if (value === 'true' && this.open) {
        this.open = false;
      }
    });

  }


  ngOnChanges(changes: SimpleChanges): void {
    if (changes['value']) {
      if (this.value == null) {
        this.value = "(Nessuno)";
      }
    }
   if (changes['items']) {
      this.items = this.items?.map((item) => {
      return item[this.key];
    })
    this.filteredItems =  [...this.items]
   }

  }

  public onInputChange(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    const inputValue = inputElement.value;

    if (this.items && this.items.length > 0) {
      const filtered = this.items.filter((item) =>
        item.toLowerCase().startsWith(inputValue.toLowerCase())
      );

      if (inputValue.length > this.previousValue.length && filtered.length > 0) {
        const matchedItem = filtered[0];

        inputElement.value = matchedItem;

        inputElement.setSelectionRange(inputValue.length, matchedItem.length);
      }
    }
    this.previousValue = inputValue;
  }


  onInputClicked(): void {
    if (this.value !== this.autocompleteInput.nativeElement.value) {
      this.value = this.autocompleteInput.nativeElement.value;
      this.emitSelectedValue();
    }
  }

  public onItemClicked(stato: string): void {
    this.value = stato;
    this.open = false;
    this.emitSelectedValue();
  }

  public emitSelectedValue(): void {
    this.onchange.emit(this.value);
  }

}
