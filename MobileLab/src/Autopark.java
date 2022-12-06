import java.util.ArrayList;
import java.util.List;

public class Autopark {

    public static void main(String[] args) {
        List<Car> park = new ArrayList<>();
        Truck car1 = new Truck("Volvo", 2011, "бензин", 20.5,
                32.2, 15.0, TruckBody.REFRIGERATOR);
        PassengerCar car2 = new PassengerCar("Hyundai County", 2019, "дизель", 16.1, 24);

        TruckPassengerCar car3 = new TruckPassengerCar("Citroen Jumpy Van", 2022, "дизель", 8.5,
                6.5, 2.2, TruckBody.TENT, 5);

        park.add(car1);
        park.add(car2);
        park.add(car3);

        Order order1 = new Order(12.5, 10, TypeCargo.PRODUCTS);
        Order order2 = new Order(13);
        Order order3 = new Order(3.5, 1.5, TypeCargo.GOODS, 4);
        Order order4 = new Order(2);


        car1.refuel();
        car3.refuel();
        car1.repair();
        car2.repair();

        car1.loading(order1);
        System.out.println(car1.getFreeVolume());

        car2.loading(order2);
        System.out.println(car2.getFreePassPlaces());

        car2.loading(order4);
        System.out.println(car2.getOrderList());

        car3.loading(order3);
        System.out.println(car3.getFreePassPlaces());
        System.out.println(car3.getFreeCapacity());

        car1.unloading(order1);
        System.out.println(car1.getFreeVolume());
        System.out.println(car1.getOrderList());

    }
}

class Car {
    private String model;
    private int year;
    private String fuelType; // вид топлива
    private double fuelRate; // расход топлива
    public List<Order> orderList = new ArrayList<>();

    public Car(String model, int year, String fuelType, double fuelRate) {
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.fuelRate = fuelRate;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    //заправить
    public void refuel() {

    }

    //отремонтировать
    public void repair() {

    }
}

//выполнение заказа
interface OrderFulfillment {
    public void loading(Order order);

    public void unloading(Order order);
}

//тип кузова
enum TruckBody {
    TENT,            //тентовый
    REFRIGERATOR,    //рефрежератор
    TANK             //цистерна
}

//тип груза
enum TypeCargo {
    GOODS,       //промтовары
    PRODUCTS,    // продукты
    LIQUIDS      // жидкости
}

// грузовые машины
class Truck extends Car implements OrderFulfillment {

    private double volumeBody; // объем кузова
    private double loadCapacity; // грузоподъемность
    private TruckBody typeBody;// тип кузова
    double freeVolume;
    double freeCapacity;

    public Truck(String model, int year, String fuelType, double fuelRate,
                 double volumeBody, double loadCapacity, TruckBody typeBody) {
        super(model, year, fuelType, fuelRate);
        this.volumeBody = volumeBody;
        this.loadCapacity = loadCapacity;
        this.typeBody = typeBody;
        freeCapacity = loadCapacity;
        freeVolume = volumeBody;
    }

    public double getFreeVolume() {
        return freeVolume;
    }

    public double getFreeCapacity() {
        return freeCapacity;
    }

    public TruckBody getTypeBody() {
        return typeBody;
    }

    public double getVolumeBody() {
        return volumeBody;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    //опломбировать кузов
    public void sealBody() {

    }

    @Override
    public void loading(Order order) {

        if (freeVolume >= order.getVolumeCargo()
                && freeCapacity >= order.getWeightCargo()
                && typeBody.equals(order.chooseTypeBody(order.getTypeCargo()))) {

            freeVolume = freeVolume - order.getVolumeCargo();
            freeCapacity = freeCapacity - order.getWeightCargo();
            orderList.add(order);

        } else System.out.println("Загрузка превышает свободное место " +
                "или тип груза не соответствует типу кузова");

    }

    @Override
    public void unloading(Order order) {
        if (orderList.contains(order)) {

            freeVolume = freeVolume + order.getVolumeCargo();
            freeCapacity = freeCapacity + order.getWeightCargo();
            orderList.remove(order);
        }
    }
}

// пассажирские машины
class PassengerCar extends Car implements OrderFulfillment {

    private int quantityPassPlaces;
    private int freePassPlaces;

    public PassengerCar(String model, int year, String fuelType, double fuelRate, int quantityPassengers) {
        super(model, year, fuelType, fuelRate);
        this.quantityPassPlaces = quantityPassengers;
        freePassPlaces = quantityPassPlaces;
    }

    public int getQuantityPassengers() {
        return quantityPassPlaces;
    }

    public int getFreePassPlaces() {
        return freePassPlaces;
    }

    public void disinfectSalon() {

    }

    @Override
    public void loading(Order order) {
        if (freePassPlaces >= order.getQuantityPassengers()) {
            freePassPlaces = freePassPlaces - order.getQuantityPassengers();
            orderList.add(order);
        } else System.out.println("Недостаточно мест для посадки");
    }

    @Override
    public void unloading(Order order) {
        if (orderList.contains(order)) {
            freePassPlaces = freePassPlaces + order.getQuantityPassengers();
            orderList.remove(order);
        }
    }
}


//грузопассажирские машины
class TruckPassengerCar extends Truck {

    private int quantityPassPlaces;
    private int freePassPlaces;

    public TruckPassengerCar(String model, int year, String fuelType, double fuelRate,
                             double volumeBody, double loadCapacity, TruckBody typeBody, int quantityPassengers) {
        super(model, year, fuelType, fuelRate, volumeBody, loadCapacity, typeBody);
        this.quantityPassPlaces = quantityPassengers;
        freePassPlaces = quantityPassPlaces;
    }

    public int getQuantityPassPlaces() {
        return quantityPassPlaces;
    }

    public void disinfectSalon() {

    }

    public int getFreePassPlaces() {
        return freePassPlaces;
    }

    @Override
    public void loading(Order order) {
        if (freeVolume >= order.getVolumeCargo()
                && freeCapacity >= order.getWeightCargo()
                && getTypeBody().equals(order.chooseTypeBody(order.getTypeCargo()))
                && freePassPlaces >= order.getQuantityPassengers()) {

            freeVolume = freeVolume - order.getVolumeCargo();
            freeCapacity = freeCapacity - order.getWeightCargo();
            freePassPlaces = freePassPlaces - order.getQuantityPassengers();
            orderList.add(order);

        } else System.out.println("Загрузка превышает свободное место " +
                "или тип груза не соответствует типу кузова," +
                "или недостаточно мест для посадки");

    }

    @Override
    public void unloading(Order order) {
        if (orderList.contains(order)) {

            freeVolume = freeVolume + order.getVolumeCargo();
            freeCapacity = freeCapacity + order.getWeightCargo();
            freePassPlaces = freePassPlaces + order.getQuantityPassengers();
            orderList.remove(order);
        }
    }
}


//заказ
class Order {
    private String startPoint;
    private String finalPoint;
    private double volumeCargo; // объем груза
    private double weightCargo; // вес груза
    private int quantityPassengers;
    private TypeCargo typeCargo;// тип груза
    private TruckBody needTruckBody; // необходимый тип кузова

// виды заказа для определенного вида трасп.средства

    public Order(int quantityPassengers) {
        this.quantityPassengers = quantityPassengers;
    }

    public Order(double volumeCargo, double weightCargo, TypeCargo typeCargo) {
        this.volumeCargo = volumeCargo;
        this.weightCargo = weightCargo;
        this.typeCargo = typeCargo;
    }


    public Order(double volumeCargo, double weightCargo, TypeCargo typeCargo, int quantityPassengers) {
        this.volumeCargo = volumeCargo;
        this.weightCargo = weightCargo;
        this.quantityPassengers = quantityPassengers;
        this.typeCargo = typeCargo;
    }

    // подбор типа кузова в зависимости от типа груза
    public TruckBody chooseTypeBody(TypeCargo typeCargo) {
        switch (typeCargo) {
            case GOODS -> {
                needTruckBody = TruckBody.TENT;
                break;
            }
            case PRODUCTS -> {
                needTruckBody = TruckBody.REFRIGERATOR;
                break;
            }
            case LIQUIDS -> needTruckBody = TruckBody.TANK;
        }
        return needTruckBody;
    }

    public double getVolumeCargo() {
        return volumeCargo;
    }

    public double getWeightCargo() {
        return weightCargo;
    }

    public TypeCargo getTypeCargo() {
        return typeCargo;
    }

    public int getQuantityPassengers() {
        return quantityPassengers;
    }
}

