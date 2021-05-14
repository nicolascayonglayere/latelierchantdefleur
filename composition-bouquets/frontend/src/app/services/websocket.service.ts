// import { Injectable, ɵSWITCH_COMPILE_INJECTABLE__POST_R3__ } from '@angular/core';
// import * as SockJS from 'sockjs-client';
// import { Client, Message } from '@stomp/stompjs';
// import { SocketResponse } from './../model/SocketResponse';
// import { InjectableRxStompConfig, RxStompService  } from '@stomp/ng2-stompjs';
// import { Observable } from 'rxjs';
// import {  WebSocketOptions } from './../model/WebSocketOptions';

// @Injectable()
// // export class WebsocketService {

// //   private serverUrl = `http://127.0.0.1:8181/atelier-chant-de-fleur/devis-response`;
// //   private stompClient;
// //   public mapEndpointSubscription: Map<string, any> = new Map();

// //   constructor(){}

// //   public async initWebSocket(): Promise<any> {
// //     return new Promise((resolve) => {
// //       if (!this.stompClient) {
// //         const client = new StompJs.Client();
// //         const ws = new SockJS(this.serverUrl);
// //         this.stompClient = new Client(ws);
// //         this.stompClient.connect({}, resolve);
// //     } else {
// //       resolve();
// //     }
// // })
// // }

// /**
//  * A WebSocket service allowing subscription to a broker.
//  */
// export class WebSocketService {
//   private obsStompConnection: Observable<any>;
//   private subscribers: Array<any> = [];
//   private subscriberIndex = 0;
//   private stompConfig: InjectableRxStompConfig = {
//     heartbeatIncoming: 0,
//     heartbeatOutgoing: 20000,
//     reconnectDelay: 10000,
//     debug: (str) => { console.log(str); }
//   };

//   constructor(
//     private stompService: RxStompService,
//     private updatedStompConfig: InjectableRxStompConfig,
//     private options: WebSocketOptions
//     ) {
//     // Update StompJs configuration.
//     this.stompConfig = {...this.stompConfig, ...this.updatedStompConfig};
//     // Initialise a list of possible subscribers.
//     this.createObservableSocket();
//     // Activate subscription to broker.
//     this.connect();
//   }

//   private createObservableSocket = () => {
//     this.obsStompConnection = new Observable(observer => {
//       const subscriberIndex = this.subscriberIndex++;
//       this.addToSubscribers({ index: subscriberIndex, observer });
//       return () => {
//         this.removeFromSubscribers(subscriberIndex);
//       };
//     });
//   }

//   private addToSubscribers = subscriber => {
//     this.subscribers.push(subscriber);
//   }

//   private removeFromSubscribers = index => {
//     for (let i = 0; i < this.subscribers.length; i++) {
//       if (i === index) {
//         this.subscribers.splice(i, 1);
//         break;
//       }
//     }
//   }

//   /**
//    * Connect and activate the client to the broker.
//    */
//   private connect = () => {
//     this.stompService.stompClient.configure(this.stompConfig);
//     this.stompService.stompClient.onConnect = this.onSocketConnect;
//     this.stompService.stompClient.onStompError = this.onSocketError;
//     this.stompService.stompClient.activate();
//   }

//   /**
//    * On each connect / reconnect, we subscribe all broker clients.
//    */
//   private onSocketConnect = frame => {
//     this.stompService.stompClient.subscribe(this.options.brokerEndpoint, this.socketListener);
//   }

//   private onSocketError = errorMsg => {
//     console.log('Broker reported error: ' + errorMsg);

//     const response: SocketResponse = {
//       type: 'ERROR',
//       message: errorMsg
//     };

//     this.subscribers.forEach(subscriber => {
//       subscriber.observer.error(response);
//     });
//   }

//   private socketListener = frame => {
//     this.subscribers.forEach(subscriber => {
//       subscriber.observer.next(this.getMessage(frame));
//     });
//   }

//   private getMessage = data => {
//     const response: SocketResponse = {
//       type: 'SUCCESS',
//       message: JSON.parse(data.body)
//     };
//     return response;
//   }

//   /**
//    * Return an observable containing a subscribers list to the broker.
//    */
//   public getObservable = () => {
//     return this.obsStompConnection;
//   }
// }
