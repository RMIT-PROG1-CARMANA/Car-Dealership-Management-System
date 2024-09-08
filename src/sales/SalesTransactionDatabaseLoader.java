//package sales;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SalesTransactionDatabaseLoader {
//    public final ArrayList<SalesTransaction> transactions = new ArrayList<>();
//    private String transactionDatabase = "";
//
//    public void loadTransactionDatabase(String transactionDatabase) {
//        try (BufferedReader br = new BufferedReader(new FileReader(transactionDatabase))) {
//            this.transactionDatabase = transactionDatabase;
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] transactionData = line.split(",");
//                SalesTransaction transaction = getSalesTransaction(transactionData);
//                transactions.add(transaction);
//            }
//        } catch (IOException e) {
//            System.err.println("Error loading transaction database: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static SalesTransaction getSalesTransaction(String[] transactionData) {
//        if (transactionData.length < 8) {
//            throw new IllegalArgumentException("Invalid transaction data: " + String.join(",", transactionData));
//        }
//
//        String transactionID = transactionData[0];
//        String transactionDate = transactionData[1];
//        String clientID = transactionData[2];
//        String salespersonID = transactionData[3];
//
//        List<String> purchasedItems = List.of(transactionData[4].split("\\|"));
//        double discount = Double.parseDouble(transactionData[5]);
//        double totalAmount = Double.parseDouble(transactionData[6]);
//        String notes = transactionData[7];
//
//        return new SalesTransaction(transactionID, transactionDate, clientID, salespersonID, purchasedItems, discount, totalAmount, notes);
//    }
//
//    public void overwriteDatabase(ArrayList<SalesTransaction> transactions) {
//        try (PrintWriter pw = new PrintWriter(transactionDatabase)) {
//            for (SalesTransaction transaction : transactions) {
//                pw.println(transaction.getTransactionID() + "," +
//                        transaction.getTransactionDate() + "," +
//                        transaction.getClientID() + "," +
//                        transaction.getSalespersonID() + "," +
//                        transaction.getSerializedPurchasedItemID() + "," + // Serialize purchased items
//                        transaction.getDiscount() + "," +
//                        transaction.getTotalAmount() + "," +
//                        transaction.getNotes());
//            }
//        } catch (IOException e) {
//            System.err.println("Error writing to transaction database: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}