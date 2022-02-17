package com.gmail.vleynik.olad.travelagency.services.tasks;

import com.gmail.vleynik.olad.travelagency.dao.SavedEntryDAO;

public class RemoveExpiredEntryTask implements Task {

    @Override
    public void run() {
        SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
        savedEntryDAO.deleteExpiredSessions();
    }
}
