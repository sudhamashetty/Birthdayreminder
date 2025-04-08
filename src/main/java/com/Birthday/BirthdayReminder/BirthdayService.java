package com.Birthday.BirthdayReminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayService {
    @Autowired
    private BirthdayRepository birthdayRepository;

    //save a new birthday entry
    public Birthday addBirthday(Birthday birthday) {
        return birthdayRepository.save(birthday);
    }

    //get all birthday
    public List<Birthday> getAllBirthdays() {
        return birthdayRepository.findAll();
    }

    //get birthdays that matches today's date
    public List<Birthday>getAllBirthdaysToday(){
        LocalDate today = LocalDate.now();
        return birthdayRepository.findByMonthAndDay(today.getMonthValue(), today.getDayOfMonth());
    }

    //get all birthdays in current month
    public List<Birthday>getBirthdaysThisMonth() {
        LocalDate today = LocalDate.now();
        return birthdayRepository.findByMonth(today.getMonthValue());
    }

    //delete birthday by id
    public void deleteBirthday(Long id) {
        birthdayRepository.deleteById(id);
    }
}
