//package com.atelierchantdefleur.bouquetcomposer.async;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//
//public class DevisEndpoint {
//
//    private final SimpMessageSendingOperations messagingTemplate;
//
//    @Autowired
//    public DevisEndpoint(SimpMessageSendingOperations messagingTemplate) {
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    public void sendMessageTest (String message){
//        String path = "/devis-response";
//        this.sendNotificationByUrl(path, message);
//    }
//
//    private void sendNotificationByUrl(String path, Object notif){
//        messagingTemplate.convertAndSend(path, notif);
//    }
//}
