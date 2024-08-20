package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      User user1 = new User("Pavel", "Dzhioev", "i@paveld.ru");
      User user2 = new User("Oleg", "Ivanov", "oleg@maul.ru");
      Car car1 = new Car("Mercedes", 250);
      Car car2 = new Car("BMW", 330);
      user1.setUserCar(car1);
      user2.setUserCar(car2);
      userService.add(user1);
      userService.add(user2);
      User userCar = userService.userByCar("Mercedes", 250);
      if (!(userCar ==null)) {
         System.out.println(userCar.getFirstName() + " " + userCar.getLastName() + " has this car");
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (!Objects.isNull(user.getUserCar())){
         System.out.println("Car = "+user.getUserCar());}
         System.out.println();
      }

      context.close();
   }
}
