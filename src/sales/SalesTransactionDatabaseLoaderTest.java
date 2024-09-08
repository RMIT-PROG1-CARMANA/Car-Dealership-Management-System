
// package sales;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// public class SalesTransactionDatabaseLoaderTest {
//     private static final String stlt = "src" + File.separator + "SalesTransaction" + File.separator + "SalesTransactionDatabase.txt";
//     private SalesTransactionDatabaseLoader loader;

//     public static void main(String[] args) throws IOException {
//         SalesTransactionDatabaseLoaderTest test = new SalesTransactionDatabaseLoaderTest();
//         test.setUp();
//         test.testLoadTransactionDatabase();
//         test.testOverwriteDatabase();
//         test.tearDown();
//     }

//     public void setUp() throws IOException {
//         loader = new SalesTransactionDatabaseLoader();
//         createTestFile();
//     }

//     private void createTestFile() throws IOException {
//         try (FileWriter writer = new FileWriter(stlt)) {
//             writer.write("1,2024-08-30,123,456,item1|item2,10.0,100.0,Test Note\n");
//             writer.write("2,2024-08-31,124,457,item3|item4,5.0,50.0,Another Note\n");
//         }
//     }

//     public void testLoadTransactionDatabase() {
//         loader.loadTransactionDatabase(stlt);
//         assert loader.transactions.size() == 2 : "testLoadTransactionDatabase failed: Expected 2 transactions but found " + loader.transactions.size();

//         validateTransaction(loader.transactions.get(0),
//                 "1", "2024-08-30", "123", "456",
//                 List.of("item1", "item2"), 10.0, 100.0, "Test Note");

//         validateTransaction(loader.transactions.get(1),
//                 "2", "2024-08-31", "124", "457",
//                 List.of("item3", "item4"), 5.0, 50.0, "Another Note");

//         System.out.println("testLoadTransactionDatabase passed");
//     }

//     public void testOverwriteDatabase() {
//         loader.loadTransactionDatabase(stlt);

//         ArrayList<SalesTransaction> newTransactions = new ArrayList<>();
//         newTransactions.add(new SalesTransaction("3", "2024-09-01", "125", "458", List.of("item5", "item6"), 15.0, 150.0, "New Note"));

//         loader.overwriteDatabase(newTransactions);
//         loader.transactions.clear(); // Clear the old data
//         loader.loadTransactionDatabase(stlt); // Reload to check

//         assert loader.transactions.size() == 1 : "testOverwriteDatabase failed: Expected 1 transaction but found " + loader.transactions.size();

//         validateTransaction(loader.transactions.getFirst(),
//                 "3", "2024-09-01", "125", "458",
//                 List.of("item5", "item6"), 15.0, 150.0, "New Note");

//         System.out.println("testOverwriteDatabase passed");
//     }

//     private void validateTransaction(SalesTransaction transaction, String expectedID, String expectedDate,
//                                      String expectedClientID, String expectedSalespersonID,
//                                      List<String> expectedItems, double expectedDiscount, double expectedAmount, String expectedNotes) {
//         assert transaction.getTransactionID().equals(expectedID) : "Incorrect transaction ID";
//         assert transaction.getTransactionDate().equals(expectedDate) : "Incorrect transaction date";
//         assert transaction.getClientID().equals(expectedClientID) : "Incorrect client ID";
//         assert transaction.getSalespersonID().equals(expectedSalespersonID) : "Incorrect salesperson ID";
//         assert transaction.getSerializedPurchasedItemID().equals(expectedItems) : "Incorrect purchased items";
//         assert transaction.getDiscount() == expectedDiscount : "Incorrect discount value";
//         assert transaction.getTotalAmount() == expectedAmount : "Incorrect total amount";
//         assert transaction.getNotes().equals(expectedNotes) : "Incorrect notes";
//     }

//     public void tearDown() {
//         File file = new File(stlt);
//         if (file.exists()) {
//             if (file.delete()) {
//                 System.out.println("Test file deleted successfully.");
//             } else {
//                 System.err.println("Failed to delete the test file.");
//             }
//         }
//     }
// }
