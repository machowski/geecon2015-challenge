package pl.allegro.promo.geecon2015.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Report implements Iterable<ReportedUser> {

    private final List<ReportedUser> users = new ArrayList<>();

    public void add(ReportedUser user) {
        this.users.add(user);
    }

    public void merge(Report report) {
        for (ReportedUser reportedUser : report) {
            add(reportedUser);
        }
    }

    @Override
    public Iterator<ReportedUser> iterator() {
        return Collections.unmodifiableCollection(users).iterator();
    }
    
    public int size() {
        return users.size();
    }
}
