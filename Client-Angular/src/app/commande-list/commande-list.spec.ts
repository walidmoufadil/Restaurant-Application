import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommandeListComponent } from './commande-list';

describe('CommandeList', () => {
  let component: CommandeListComponent;
  let fixture: ComponentFixture<CommandeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommandeListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CommandeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
