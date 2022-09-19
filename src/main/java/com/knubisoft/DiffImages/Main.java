package com.knubisoft.DiffImages;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        String file = "D:\\Projects\\DifferenceImages\\src\\main\\resources\\test1.jpg";
        String file2 = "D:\\Projects\\DifferenceImages\\src\\main\\resources\\test2.jpg";

        SearcherImageDiff searcherImageDiff = new SearcherImageDiff();
        searcherImageDiff.launchFinderImageDiff(file, file2);
    }

}
