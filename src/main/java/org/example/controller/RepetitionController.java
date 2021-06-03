package org.example.controller;

import org.example.entity.Actor;
import org.example.entity.Repetition;
import org.example.repository.IRepo;
import org.example.repository.impl.ActorRepoImpl;
import org.example.repository.impl.RepetitionRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class RepetitionController {
    private IRepo iRepo = new RepetitionRepoImpl();

    public Repetition getRepetitionById(Long id) throws SQLException {
        return (Repetition) iRepo.getById(id);
    }

    public Repetition editRepetition(Repetition repetition) throws SQLException {
        return (Repetition) iRepo.update(repetition);
    }

    public List<Repetition> getAllRepetition() throws SQLException {
        return iRepo.getAll();
    }

    public void saveRepetition(Repetition repetition) throws SQLException {
        iRepo.add(repetition);
    }

    public void deleteRepetition(Long id) throws SQLException{
        iRepo.delete(id);
    }
}
