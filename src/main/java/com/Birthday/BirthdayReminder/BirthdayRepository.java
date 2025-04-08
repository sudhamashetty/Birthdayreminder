package com.Birthday.BirthdayReminder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday,Long> {
    @Query("SELECT b FROM Birthday b WHERE MONTH(b.date) = ?1 AND DAY(b.date)= ?2")
    List<Birthday> findByMonthAndDay(int month,int day);

    @Query("SELECT b FROM Birthday b WHERE MONTH(b.date) = ?1")
    List<Birthday> findByMonth(int month);
    }
