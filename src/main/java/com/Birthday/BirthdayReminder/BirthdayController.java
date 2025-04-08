package com.Birthday.BirthdayReminder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Birthday")
@CrossOrigin("*") //allows access from any frontend
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

  @PostMapping
    public Birthday addBirthday(@RequestBody Birthday birthday){
      return birthdayService.addBirthday(birthday);
  }

  @GetMapping
    public List<Birthday>getAllBirthdays(){
      return birthdayService.getAllBirthdays();
  }
  @GetMapping("/today")
    public List<Birthday>getBirthdaysToday() {
     return birthdayService.getAllBirthdaysToday();
  }

  @GetMapping("/month")
    public List<Birthday>getBirthdaysTodayMonth() {
      return birthdayService.getBirthdaysThisMonth();
  }
  @DeleteMapping("/{id}")
    public void deleteBirthdays(@PathVariable Long id) {
      birthdayService.deleteBirthday(id);
  }
}

