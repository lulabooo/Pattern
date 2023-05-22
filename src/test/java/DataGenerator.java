import com.github.javafaker.Faker;
import lombok.Value;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {

    private DataGenerator() {

    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{"Воронеж", "Иваново", "Калуга", "Кострома", "Курск", "Липецк", "Москва",
                "Орел", "Рязань", "Смоленск", "Тамбов", "Тверь", "Тула", "Ярославль", "Архангельск", "Вологда",
                "Калининград", "Петрозаводск", "Сыктывкар", "Санкт-Петербург", "Мурманск", "Салехард", "Великий Новгород",
                "Псков", "Санкт-Петербург", "Уфа", "Киров", "Йошкар-Ола", "Саранск", "Нижний Новгород", "Оренбург",
                "Пенза", "Пермь", "Самара", "Саратов", "Казань", "Ижевск", "Ульяновск", "Чебоксары", "Курган", "Екатеринбург",
                "Тюмень", "Югра", "Ханты-Мансийск", "Челябинск", "Салехард", "Горно-Алтайск", "Барнаул", "Улан-Удэ", "Чита",
                "Иркутск", "Кемерово", "Красноярск", "Новосибирск", "Омск", "Томск", "Кызыл", "Абакан", "Благовещенск",
                "Биробиджан", "Петропавловск-Камчатский", "Магадан", "Владивосток", "Якутск", "Южно-Сахалинск",
                "Хабаровск", "Анадырь", "Майкоп", "Астрахань", "Волгоград", "Элиста", "Краснодар", "Ростов-на-Дону", "Махачкала",
                "Магас", "Нальчик", "Черкесск", "Владикавказ", "Ставрополь", "Грозный"};
        return cities[new Random().nextInt(cities.length)];

    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhoneNumber(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {

        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhoneNumber(locale));
        }

        @Value
        public static class UserInfo {
            String city;
            String name;
            String phone;
        }
        @Value
        public static class InvalidUserInfo {
            String city;
            String name;
            String phone;

        }
    }

}