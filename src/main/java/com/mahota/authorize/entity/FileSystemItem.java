package com.mahota.authorize.entity;
public class FileSystemItem
{
    private String name;
    private String directory;

    public FileSystemItem(String name, String directory) {
        this.name = name;
        this.directory = directory;
    }

    public String getName()
    {
        return name;
    }
}
