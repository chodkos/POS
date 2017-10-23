package com.pos;

import com.pos.item.ItemService;
import com.pos.reader.Reader;
import com.pos.transaction.Transaction;

import java.util.Scanner;

public class PointOfSale {

    public static void main(String[] args) {
        Reader reader = null;
        Scanner in = new Scanner(System.in);

        Transaction transaction = new Transaction(null, null, null, null, null);


        transaction.startTransaction();

        transaction.checkout();



    }

}
