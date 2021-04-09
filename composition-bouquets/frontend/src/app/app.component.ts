import {faCaretLeft, faCaretRight} from '@fortawesome/free-solid-svg-icons';
import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  faCaretRight = faCaretRight;
  faCaretLeft = faCaretLeft;
  isCollapsedTot = false;

  // clientSelected: Client;
  //
  // selectClient(event: Client){
  //   this.clientSelected = event;
  // }


}
