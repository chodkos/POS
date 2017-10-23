package com.pos;

import com.pos.transaction.Transaction;

public class PointOfSale {

    public static void main(String[] args) {

        //Transaction transaction = new Transaction(null, null, null, null, null);
        Transaction transaction = new Transaction();

        transaction.startTransaction();

        transaction.checkout();



    }

}
