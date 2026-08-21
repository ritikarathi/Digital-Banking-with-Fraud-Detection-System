package com.banking.transactionservice.entity;

// pending -> processing -> completed (clean transaction)
//                         -> pending verification (suspicious detected)
//                               -> completed (verified)
//                               -> flagged (SAGA Refund)
//                          -> failed
//                          -> Flagged

public enum TransactionStatus {

    PENDING,
    PROCESSING,
    PENDING_VERIFICATION,
    COMPLETED,
    FAILED,
    FLAGGED


}
