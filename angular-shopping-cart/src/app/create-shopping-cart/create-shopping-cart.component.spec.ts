import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateShoppingCartComponent } from './create-shopping-cart.component';

describe('CreateShoppingCartComponent', () => {
  let component: CreateShoppingCartComponent;
  let fixture: ComponentFixture<CreateShoppingCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateShoppingCartComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateShoppingCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
