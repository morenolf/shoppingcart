import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteShoppingCartComponent } from './delete-shopping-cart.component';

describe('DeleteShoppingCartComponent', () => {
  let component: DeleteShoppingCartComponent;
  let fixture: ComponentFixture<DeleteShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteShoppingCartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
