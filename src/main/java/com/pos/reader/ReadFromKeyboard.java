package com.pos.reader;

import java.util.Scanner;

public class ReadFromKeyboard implements Reader {

    private String code;

    public ReadFromKeyboard() {
    }

    @Override
    public String read() {
        System.out.println("Waiting for code: ");
        Scanner in = new Scanner(System.in);
        code = in.nextLine();
        return code;
    }
}
