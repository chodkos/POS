package com.pos.reader;

import com.pos.item.Code;

import java.util.Scanner;

public class ReadFromKeyboard implements Reader {

    private String code;

    public ReadFromKeyboard() {
    }

    @Override
    public Code read() {
        System.out.println("Waiting for code: ");
        //Scanner in = new Scanner(System.in);
        code = "SAS";
        return new Code(code);
    }
}
