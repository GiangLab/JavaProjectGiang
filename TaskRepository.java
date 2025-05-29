package com.example.GiangFroject.Repository;

import com.example.GiangFroject.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserIdAndDeletedFalse(Long userId);

    List<Task> findByUserIdAndCompletedFalseAndDeletedFalse(Long userId);

    // ✅ Thêm dòng này để fix lỗi:
    List<Task> findByTargetDateBeforeAndCompletedFalse(LocalDateTime dateTime);
}
