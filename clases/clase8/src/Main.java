import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Vuelo vuelo1 = new Vuelo("AO123", "American", "Houston", "New York");
        Vuelo vuelo2 = new Vuelo("A12F2", "Emirates", "Houston", "New York");
        Vuelo vuelo3 = new Vuelo("A421S", "Latam", "Houston", "Atlanta");
        Vuelo vuelo4 = new Vuelo("AA41D", "Pluna", "Houston", "New York");


        for (int i = 1; i < 31; i++) {
            vuelo1.agregarFecha(LocalDate.of(2022, 10, i));
            vuelo2.agregarFecha(LocalDate.of(2022,10,i));
            vuelo3.agregarFecha(LocalDate.of(2022,10,i));
            vuelo4.agregarFecha(LocalDate.of(2022,10,i));
        }

        Hotel hotel1 = new Hotel("Hilton", "New York");
        Hotel hotel2 = new Hotel("Trump", "New York");
        Hotel hotel3 = new Hotel("Sheraton", "Atlanta");
        Hotel hotel4 = new Hotel("Raddison", "New York");

        for (int i = 1; i < 31; i++) {
            hotel1.agregarFecha(LocalDate.of(2022, 10, i));
            hotel2.agregarFecha(LocalDate.of(2022,10,i));
            hotel3.agregarFecha(LocalDate.of(2022,10,i));
            hotel4.agregarFecha(LocalDate.of(2022,10,i));
        }


        BusquedaImpl buscador = new BusquedaImpl();

        buscador.agregarHotel(hotel1);
        buscador.agregarHotel(hotel2);
        buscador.agregarHotel(hotel3);
        buscador.agregarHotel(hotel4);

        buscador.agregarVuelo(vuelo1);
        buscador.agregarVuelo(vuelo2);
        buscador.agregarVuelo(vuelo3);
        buscador.agregarVuelo(vuelo4);

        buscador.buscar("Houston", LocalDate.of(2022,10,5), LocalDate.of(2022,10,10), "New York");

    }


}
