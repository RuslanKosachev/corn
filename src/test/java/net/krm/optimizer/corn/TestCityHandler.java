package net.krm.optimizer.corn;


import org.junit.Test;

public class TestCityHandler {

    @Test
    public void testAddCity() {
        CityHandler handler = new CityHandler();

        handler.setCentralCity(51.528594f, 46.027980f, "Саратов");

        // зона одц
        handler.addCity(51.475426f, 46.101825f, 4,  "Энгельс");
        handler.addCity(51.709724f, 46.756890f, 11, "Маркс");
        handler.addCity(51.379050f, 46.845092f, 6,  "Степное Совет р/н");
        handler.addCity(51.017050f, 45.698992f, 1,  "Красноармейск");
        handler.addCity(51.674724f, 45.592202f, 3,  "Татищево");
        handler.addCity(51.877897f, 44.999178f, 11, "Аткарск");
        handler.addCity(52.317210f, 45.400085f, 2,  "Петроовск");
        handler.addCity(52.129152f, 46.071225f, 4,  "Новыебурасы");
        handler.addCity(52.271053f, 46.418703f, 3,  "БазарныйКарабулак");
        // левобережье
        handler.addCity(51.854436f, 48.603245f, 25, "Горный Красноп. р/н");
        handler.addCity(52.006740f, 48.765169f, 22, "Пугачёв");
        handler.addCity(51.861142f, 50.351982f, 11, "Перелюб");
        handler.addCity(52.013710f, 47.799450f, 10, "Балаково");
        handler.addCity(52.477238f, 48.212410f, 9,  "Духовницкое");
        handler.addCity(51.352175f, 48.278928f, 8,  "Ершов");
        handler.addCity(51.232567f, 48.764057f, 2,  "Дергачи");
        handler.addCity(50.949627f, 46.967073f, 2,  "Красный Кут");
        handler.addCity(52.267664f, 49.106488f, 1,  "Ивантеевка");
        handler.addCity(51.198688f, 49.728772f, 1,  "Озинки");
        // правобережье
        handler.addCity(51.934370f, 43.507977f, 19, "Аркадак");
        handler.addCity(51.542366f, 43.160397f, 17, "Балашов");
        handler.addCity(51.179126f, 43.705091f, 17, "Самойловка");
        handler.addCity(52.052330f, 44.346272f, 11, "Екатериновка");
        handler.addCity(51.748780f, 42.748467f, 11, "Романовка");
        handler.addCity(51.503167f, 44.478237f, 9,  "Калининск");
        handler.addCity(51.986504f, 43.272146f, 8,  "Турки");
        handler.addCity(52.262443f, 43.798282f, 4,  "Ртищево");
        handler.addCity(52.039187f, 47.385421f, 4,  "Вольск");

        handler.calcCoefficientPlacement();

        handler.exclusionOfCitiesRelativeToCentral();

        handler.aggregatedCitiesByServiceArea();

        handler.getCitiesForDealerships();
    }
}
