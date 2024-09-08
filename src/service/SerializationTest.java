package service;

import service.ServiceFileHandler;
import part.AutoPart;
import service.Service;

import java.io.*;
import java.util.*;

public class SerializationTest {
    public static void main(String[] args) {
        try {
            // Check parts
            ObjectInputStream partsOis = new ObjectInputStream(new FileInputStream("src/DataBase/parts.ser"));
            Object partsObject = partsOis.readObject();
            System.out.println("Parts file contains object of type: " + partsObject.getClass().getName());
            if (partsObject instanceof List<?>) {
                List<AutoPart> partsList = (List<AutoPart>) partsObject;
                System.out.println("Loaded parts: " + partsList);
            }
            partsOis.close();

            // Check services
            ObjectInputStream servicesOis = new ObjectInputStream(new FileInputStream("src/DataBase/services.ser"));
            Object servicesObject = servicesOis.readObject();
            System.out.println("Services file contains object of type: " + servicesObject.getClass().getName());
            if (servicesObject instanceof List<?>) {
                List<Service> servicesList = (List<Service>) servicesObject;
                System.out.println("Loaded services: " + servicesList);
            }
            servicesOis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during serialization test: " + e.getMessage());
        }
    }
}
