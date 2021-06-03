package org.example.controller;

import org.example.entity.Performance;
import org.example.repository.IRepo;
import org.example.repository.impl.PerformanceRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class PerformanceController {
    private IRepo iRepo = new PerformanceRepoImpl();

    public Performance getPerformanceById(Long id) throws SQLException {
        return (Performance) iRepo.getById(id);
    }

    public Performance editPerformance(Performance performance) throws SQLException {
        return (Performance) iRepo.update(performance);
    }

    public List<Performance> getAllPerformances() throws SQLException {
        return iRepo.getAll();
    }

    public void savePerformance(Performance performance) throws SQLException {
        iRepo.add(performance);
    }

    public void deletePerformance(Long id) throws SQLException{
        iRepo.delete(id);
    }
}
