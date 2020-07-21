package com.sukkit;

public class Sukkit {

    public static void main(String[] args) {
        String[] files = [
                "https://github.com/pmmp/PocketMine-MP/releases/download/3.14.2/PocketMine-MP.phar",
                "https://github.com/pmmp/PocketMine-MP/releases/download/3.14.2/start.cmd",
            ];
        for (String (file) : files) {
            URL url = new URL(file);
            BufferedInputStream in = new BufferedInputStream(url.openStream());
        }
    }

}