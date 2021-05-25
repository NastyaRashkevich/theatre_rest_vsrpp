package org.example.controller;

import org.example.entity.Actor;
import org.example.repository.IRepo;
import org.example.repository.impl.ActorRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class ActorController {
    private IRepo iRepo = new ActorRepoImpl();

    public Actor getActorById(Long id) throws SQLException {
        return (Actor) iRepo.getById(id);
    }

    public Actor editActor(Actor actor) throws SQLException {
        return (Actor) iRepo.update(actor);
    }

    public List<Actor> getAllActors() throws SQLException {
        return iRepo.getAll();
    }

    public void saveActor(Actor actor) throws SQLException {
        iRepo.add(actor);
    }

    public void deleteActor(Long id) throws SQLException{
        iRepo.delete(id);
    }
}