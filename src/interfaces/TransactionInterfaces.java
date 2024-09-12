package interfaces;

public interface TransactionInterfaces {
    void addTransaction();
    void displayAllTransactions();
    // Soft delete a transaction
    void deleteTransaction();
    void displayTransactionsSortByPrice();
    void displayTransactionsByClientID();
    void displayTransactionsByID();
}
