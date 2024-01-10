package service;

import domain.Weather;
import repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public void createSchema() {
        repo.createSchema();
    }

    public void AddinSchema() {
        repo.AddInSchema();
    }

    public ArrayList<Weather> getAll() throws Exception{
        var reports = repo.getAll();
        if (reports == null)
            throw new Exception("Could not read");
        return reports;}

}
