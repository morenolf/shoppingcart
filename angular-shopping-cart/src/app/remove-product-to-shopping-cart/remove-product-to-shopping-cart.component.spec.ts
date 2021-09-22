import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoveProductToShoppingCartComponent } from './remove-product-to-shopping-cart.component';

describe('RemoveProductToShoppingCartComponent', () => {
  let component: RemoveProductToShoppingCartComponent;
  let fixture: ComponentFixture<RemoveProductToShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RemoveProductToShoppingCartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoveProductToShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
