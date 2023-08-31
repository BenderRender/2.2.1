package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.add(new User("Dominic", "Toretto", "toretto@mail.ru",new Car("Dodge Charger", "R/T")));
      userService.add(new User("Brian", "O'Conner", "o'conner2@mail.ru",new Car("Nissan Skyline", "GT-R R34")));
      userService.add(new User("Roman", "Pearce", "pearce@mail.ru",new Car("Chevrolet Camaro", "Z28")));
      userService.add(new User("Han", "Lue", "lue@mail.ru",new Car("Mazda", "RX-7")));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+ user.getCar());
         System.out.println();
      }

      List<User> user = userService.findUserByAuto("Mazda", "RX-7");
      for (User us : user) {
         System.out.println("User: " + us.getFirstName() + " " + us.getLastName());
      }

      context.close();
   }
}
