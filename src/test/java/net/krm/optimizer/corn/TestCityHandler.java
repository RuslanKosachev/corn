package net.krm.optimizer.corn;


import org.junit.Test;

public class TestCityHandler {

    @Test
    public void testAddCity() {

        City sratov        = new City(51.528594f, 46.027980f, "Саратов");

        // зона одц
        City engels              = new City(51.475426f, 46.101825f, 4, "Энгельс");
        City marx                = new City(51.709724f, 46.756890f, 11, "Маркс");
        City stepnoe             = new City(51.379050f, 46.845092f, 6, "Степное Совет р-н");
        City krasnoarmeysk       = new City(51.017050f, 45.698992f, 1, "Красноармейск");
        City tatishchevo         = new City(51.674724f, 45.592202f, 3, "Татищево");
        City atkarsk             = new City(51.877897f, 44.999178f, 11, "Аткарск");
        City petrovsk            = new City(52.317210f, 45.400085f, 2, "Петроовск");
        City novoburasy          = new City(52.129152f, 46.071225f, 4, "Новыебурасы");
        City bazarnyKarabulaksky = new City(52.271053f, 46.418703f, 3, "БазарныйКарабулак");
        // левобережье
        City gorny               = new City(51.854436f, 48.603245f, 25, "Горный Краснопартизанский р-н");
        City pugachev            = new City(52.006740f, 48.765169f, 22, "Пугачёв");
        City perelyub            = new City(51.861142f, 50.351982f, 11, "Перелюб");
        City balakovo            = new City(52.013710f, 47.799450f, 10, "Балаково");
        City duhovnitskoe        = new City(52.477238f, 48.212410f, 9, "Духовницкое");
        City ershov              = new City(51.352175f, 48.278928f, 8, "Ершов");
        City dergachi            = new City(51.232567f, 48.764057f, 2, "Дергачи");
        City redKut              = new City(50.949627f, 46.967073f, 2, "Красный Кут");
        City ivanteevka          = new City(52.267664f, 49.106488f, 1, "Ивантеевка");
        City ozinki              = new City(51.198688f, 49.728772f, 1, "Озинки");
        // правобережье
        City arkadak             = new City(51.93437f, 43.507977f, 19, "Аркадак");
        City balashov            = new City(51.542366f, 43.160397f, 17, "Балашов");
        City samoilovka          = new City(51.179126f, 43.705091f, 17, "Самойловка");
        City ekaterinovka        = new City(52.05233f, 44.346272f, 11, "Екатериновка");
        City romanovka           = new City(51.74878f, 42.748467f, 11, "Романовка");
        City kalininsk           = new City(51.503167f, 44.478237f, 9, "Калининск");
        City turki               = new City(51.986504f, 43.272146f, 8, "Турки");
        City rtischevo           = new City(52.262443f, 43.798282f, 4, "Ртищево");
        City volsk               = new City(52.039187f, 47.385421f, 4, "Вольск");

        CityHandler handler = new CityHandler();

        handler.setCentralCity(sratov);

        handler.addCity(engels);
        handler.addCity(marx);
        handler.addCity(stepnoe);
        handler.addCity(krasnoarmeysk);
        handler.addCity(tatishchevo);
        handler.addCity(atkarsk);
        handler.addCity(petrovsk);
        handler.addCity(novoburasy);
        handler.addCity(bazarnyKarabulaksky);
        handler.addCity(gorny);
        handler.addCity(pugachev);
        handler.addCity(perelyub);
        handler.addCity(balakovo);
        handler.addCity(duhovnitskoe);
        handler.addCity(ershov);
        handler.addCity(dergachi);
        handler.addCity(redKut);
        handler.addCity(ivanteevka);
        handler.addCity(ozinki);
        handler.addCity(arkadak);
        handler.addCity(balashov);
        handler.addCity(samoilovka);
        handler.addCity(ekaterinovka);
        handler.addCity(romanovka);
        handler.addCity(kalininsk);
        handler.addCity(turki);
        handler.addCity(rtischevo);
        handler.addCity(volsk);

        handler.calcCoefficientPlacement();

        handler.filterRelativeCentralCity();
        System.out.println("CitiesPossible             n=" + handler.getCitiesPossible().size());

        handler.aggregatedByServiceArea();
        System.out.println("aggregatedByServiceArea    n=" + handler.getAggregatedByServiceArea().size());
        System.out.println("aggregatedByServiceArea    n=" + handler.getAggregatedByServiceArea());



    }
}
