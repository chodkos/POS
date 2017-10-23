package com.pos.reader;

import com.pos.item.Code;

public class StubbedReader implements Reader {

    private String code;

    public StubbedReader() {
    }

    @Override
    public Code read() {
        System.out.println("Code Scanning: ");
        //Scanner in = new Scanner(System.in);
        code = "SAS";
        return new Code(code);
    }
}
