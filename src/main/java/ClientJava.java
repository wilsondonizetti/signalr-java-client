// Copyright (c) .NET Foundation. All rights reserved.
// Licensed under the Apache License, Version 2.0. See License.txt in the project root for license information.

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.Scanner;

public class ClientJava {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the URL of the SignalR Chat you want to join");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        String input;
        input = reader.nextLine();

        HubConnection hubConnection = HubConnectionBuilder.create(input)
                .build();

        hubConnection.on("ReceiveMessage", (message) -> {
            System.out.println("Java Client New Message: " + message);
        }, String.class);

        hubConnection.on("ReceiveObjectMessage", (message) -> {
            System.out.println("Java Client New Message Object: " + message);
        }, String.class);

        //This is a blocking call
        hubConnection.start().blockingAwait();

        while (!input.equals("leave")){
            input = reader.nextLine();
            //hubConnection.send("ReceiveMessage", "Leave Java Client");
        }
        reader.close();

        hubConnection.stop();
    }
}