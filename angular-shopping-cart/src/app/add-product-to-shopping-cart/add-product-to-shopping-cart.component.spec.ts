import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProductToShoppingCartComponent } from './add-product-to-shopping-cart.component';

describe('AddProductToShoppingCartComponent', () => {
  let component: AddProductToShoppingCartComponent;
  let fixture: ComponentFixture<AddProductToShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddProductToShoppingCartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProductToShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
