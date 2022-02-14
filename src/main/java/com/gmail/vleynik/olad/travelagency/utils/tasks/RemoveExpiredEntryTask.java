package com.gmail.vleynik.olad.travelagency.utils.tasks;

import com.gmail.vleynik.olad.travelagency.dao.SavedEntryDAO;

public class RemoveExpiredEntryTask implements Task {

    @Override
    public void run() {
        SavedEntryDAO savedEntryDAO = new SavedEntryDAO();
        savedEntryDAO.deleteExpiredSessions();
    }
}
