package com.concurrente;

import com.concurrente.git.Git;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Creating a new Git repository");

        Git git = new Git();
        git.add("Hello world!", "hello.txt");

    }
}