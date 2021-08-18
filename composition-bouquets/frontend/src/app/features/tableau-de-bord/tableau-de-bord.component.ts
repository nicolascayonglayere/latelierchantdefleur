import {ChangeDetectionStrategy, Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {CalendarEvent, CalendarEventAction, CalendarEventTimesChangedEvent, CalendarView} from 'angular-calendar';
import {Subject} from 'rxjs';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours, parseISO
} from 'date-fns';
import {EvenementService} from "../../services/evenement.service";
import {Evenement} from "../../model/Evenement";
import { SnackbarSuccessComponent } from 'src/app/layout/snackbar/snackbar-success/snackbar-success.component';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'app-tableau-de-bord',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './tableau-de-bord.component.html',
  styleUrls: ['./tableau-de-bord.component.css']
})
export class TableauDeBordComponent implements OnInit{
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;
  CalendarView = CalendarView;
  viewDate: Date = new Date();
  locale = 'fr';
  refresh: Subject<any> = new Subject();
  events: CalendarEvent[] = [];
  evenements: Evenement[] = [];
  
  activeDayIsOpen = true;

  configSuccess: MatSnackBarConfig = {
    panelClass: 'snack-bar-success',
    duration: 1000,
  };

  configFailed: MatSnackBarConfig = {
    panelClass: 'snack-bar-failed',
    duration: 1000,
  };

  constructor(private evtService: EvenementService, private snackBar: MatSnackBar, private router: Router) {}

  ngOnInit(): void {
    this.evtService.getAll();
    this.evtService.currentAllEvenement.subscribe(data => {
      data.forEach(e => {
        this.evenements.push(e);
        this.constructionEvent(e);
      });
    });
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
                      event,
                      newStart,
                      newEnd,
                    }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent(event);
  }

  handleEvent(event: CalendarEvent): void {
    let evtToUpdate = this.evenements.filter(e => event.id === e.id)[0];
    evtToUpdate.datePrevue = event.start;
    this.evtService.update(evtToUpdate).subscribe(data =>{
      this.snackBar.openFromComponent(SnackbarSuccessComponent, {
        ...this.configSuccess,
        data: 'Enregistrement effectué !'
      }),
        error => {
          this.snackBar.openFromComponent(SnackbarSuccessComponent, {
            ...this.configFailed,
            data: 'Erreur lors de la sauvegarde !'
          });
      };
    });
  }

  goTo(event: CalendarEvent): void{
    this.router.navigate(['/atelier-chant-de-fleur', 'evenements', event.id]);
  }

  setView(view: CalendarView): void {
    this.view = view;
  }

  closeOpenMonthViewDay(): void {
    this.activeDayIsOpen = false;
  }

  private constructionEvent(evt: Evenement): void{
    this.events = [
      ...this.events,
      {
        id: evt.id,
        title: evt.nom,
        start: parseISO(evt.datePrevue.toLocaleString()),
        end: parseISO(evt.datePrevue.toLocaleString()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }
}
