package org.example.controller;

import org.example.entity.Director;
import org.example.repository.IRepo;
import org.example.repository.impl.DirectorRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class DirectorController {
    private IRepo iRepo = new DirectorRepoImpl();

    public Director getDirectorById(Long id) throws SQLException {
        return (Director) iRepo.getById(id);
    }

    public Director editDirector(Director director) throws SQLException {
        return (Director) iRepo.update(director);
    }

    public List<Director> getAllDirectors() throws SQLException {
        return iRepo.getAll();
    }

    public void saveDirector(Director director) throws SQLException {
        iRepo.add(director);
    }

    public void deleteDirector(Long id) throws SQLException{
        iRepo.delete(id);
    }
}
