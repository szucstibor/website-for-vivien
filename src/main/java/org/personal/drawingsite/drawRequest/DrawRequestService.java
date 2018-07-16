package org.personal.drawingsite.drawRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DrawRequestService {

    @Autowired
    DrawRequestRepository drr;

    public DrawRequestService() {
    }

    public void saveRequest(Map<String, String> request) {
        DrawRequest drawRequest = new DrawRequest(request.get("name"), request.get("description"), request.get("username"));
        drr.save(drawRequest);
    }

    public List<DrawRequest> getAllAcceptedRequests() {
        return drr.findByAcceptedIsTrueAndDoneIsFalseAndActiveIsFalse();
    }

    public DrawRequest getActiveRequest() {
        return drr.findByActiveIsTrueAndDoneIsFalse();
    }

    public List<DrawRequest> getAllNotAcceptedRequests() {
        return drr.findByAcceptedIsFalseAndDoneIsFalse();
    }

    public void setAcceptedToTrue(long requestId) {
        Optional<DrawRequest> request = drr.findById(requestId);
        request.get().setAccepted(true);
        drr.save(request.get());
    }

    public void removeCurrentlyActive() {
        DrawRequest active = drr.findByActiveIsTrue();
        active.setActive(false);
        active.setDone(true);
        drr.save(active);
    }


    public void focusOnRequest(Long requestId) {
        Optional<DrawRequest> currentlyActive = Optional.ofNullable(drr.findByActiveIsTrue());
        if (currentlyActive.isPresent()) {
            currentlyActive.get().setActive(false);
            drr.save(currentlyActive.get());
        }
        Optional<DrawRequest> focus = drr.findById(requestId);
        focus.get().setActive(true);
        drr.save(focus.get());
    }

    public void removeRequest(long requestId) {
        drr.removeById(requestId);
    }

    public void resetAll() {
        List<DrawRequest> all = drr.findAll();
        for (DrawRequest request : all) {
            request.setAccepted(false);
            request.setDone(false);
            request.setActive(false);
            drr.save(request);
        }
    }
}


