package interfaces;

public interface TransactionInterfaces {
    void addTransaction();
    void displayAllTransactions();

    // Soft delete a transaction
    void deleteTransaction(String transactionID);

    void displayTransactionsSortByPrice();
    void displayTransactionsByClientID();
    void displayTransactionsByID();
}
