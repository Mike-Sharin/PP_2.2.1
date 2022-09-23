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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      user5.setCar(new Car("model5", 5));
      userService.add(user5);

      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car6 = new Car("model6", 6);
      user6.setCar(car6);
      userService.add(user6);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {
            System.out.println(" Id car = " + user.getCar().getId());
            System.out.println(" Model car = " + user.getCar().getModel());
            System.out.println(" Series car = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      User userFind = userService.findUserCar(car6);
      System.out.println("Id = " + userFind.getId()
              + "\nFirst Name = "+userFind.getFirstName()
              + "\nLast Name = "+userFind.getLastName()
              + "\nEmail = "+userFind.getEmail()
              + "\n Id car = " + userFind.getCar().getId()
              + "\n Model car = " + userFind.getCar().getModel()
              + "\n Series car = " + userFind.getCar().getSeries()
      );

      userFind = userService.findUserCar(new Car("model6", 6));
      System.out.println("Id = " + userFind.getId()
              + "\nFirst Name = "+userFind.getFirstName()
              + "\nLast Name = "+userFind.getLastName()
              + "\nEmail = "+userFind.getEmail()
              + "\n Id car = " + userFind.getCar().getId()
              + "\n Model car = " + userFind.getCar().getModel()
              + "\n Series car = " + userFind.getCar().getSeries()
      );

      context.close();
   }
}
