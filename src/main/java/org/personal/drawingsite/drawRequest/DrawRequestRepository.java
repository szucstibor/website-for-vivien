package org.personal.drawingsite.drawRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DrawRequestRepository extends JpaRepository<DrawRequest, Long> {
    List<DrawRequest> findByAcceptedIsTrue();

    DrawRequest findByActiveIsTrueAndDoneIsFalse();

    List<DrawRequest> findByAcceptedIsFalseAndDoneIsFalse();

    @Transactional
    void removeById(Long requestId);

    DrawRequest findByActiveIsTrue();

    List<DrawRequest> findByAcceptedIsTrueAndDoneIsFalseAndActiveIsFalse();

}
