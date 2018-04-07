package com.linkresearchtools.pandi;

import com.linkresearchtools.pandi.io.ui.Browser;
import com.linkresearchtools.pandi.io.process.IProcessor;
import com.linkresearchtools.pandi.io.process.BrowserProcessor;

public class Main {

    public static void main(String[] args)
    {
        IProcessor reader = new BrowserProcessor();
        Browser frame = new Browser("Url Data Extractor", reader);
        frame.setVisible(true);
    }
}
